package itmo.programming.server.core;

import itmo.programming.common.network.NetworkSerializer;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.manager.CollectionManager;
import itmo.programming.server.manager.CommandManager;
import itmo.programming.server.manager.ConsoleManager;
import itmo.programming.server.manager.FileManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Главный класс сервера.
 */
public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());
    private static final int BUFFER_SIZE = 65536;

    private static ConsoleManager consoleManager;
    private final int port;
    private final String fileName;
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;
    private DatagramChannel channel;
    private Selector selector;
    private boolean running;

    /**
     * Создает новый экземпляр сервера.
     *
     * @param port номер порта для прослушивания
     * @param fileName имя файла для хранения коллекции
     */
    public Server(int port, String fileName) {
        this.port = port;
        this.fileName = fileName;
        this.collectionManager = new CollectionManager();
        this.commandManager = new CommandManager(collectionManager);
        this.consoleManager = new ConsoleManager();
    }

    /**
     * Запускает сервер.
     */
    public void start() {
        try {
            // Инициализация канала и селектора
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.bind(new InetSocketAddress(port));
            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ);
            running = true;

            final FileManager fileManager = new FileManager(collectionManager);
            try {
                // Загрузка коллекции из файла
                fileManager.loadFromFile(fileName);
                logger.log(Level.INFO, "Коллекция загружена: " + fileName);
            } catch (FileNotFoundException e) {
                logger.log(Level.SEVERE, "Файл не найден: "
                         + fileName + "\nВведите корректное название "
                        + "или путь к нему в аргументах командной строки и запустите сервер");

                System.exit(0);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Ошибка при чтении файла: " + fileName, e);
                System.exit(0);
            }

            logger.info("Сервер запущен на порту " + port);

            new Thread(() -> {
                while (running) {
                    String input = null;
                    try {
                        input = consoleManager.readInput();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if ("save".equalsIgnoreCase(input)) {
                        try {
                            fileManager.saveToFile(fileName);
                            System.out.println("Коллекция сохранена!");
                        } catch (IOException e) {
                            logger.log(Level.SEVERE,
                                    "Коллекция не была сохранена в: " + fileName, e);
                        }

                    }
                    if ("exit".equalsIgnoreCase(input)) {
                        running = false;

                        try {
                            fileManager.saveToFile(fileName);
                        } catch (IOException e) {
                            logger.log(Level.WARNING,
                                    "Коллекция не была сохранена в файл: " + fileName, e);
                        }

                        System.out.println("Сервер останавливается...");
                        System.exit(0);
                    }
                }
            }).start();
            // Основной цикл обработки запросов
            while (running) {


                selector.select();
                final Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

                while (keyIterator.hasNext()) {
                    final SelectionKey key = keyIterator.next();
                    keyIterator.remove();

                    if (key.isReadable()) {
                        handleRequest(key);
                    }
                }

            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при работе сервера", e);
        } finally {
            stop();
        }
    }

    /**
     * Останавливает сервер.
     */
    public void stop() {
        final FileManager fileManager = new FileManager(collectionManager);
        running = false;
        try {
            if (selector != null) {
                selector.close();
            }
            if (channel != null) {
                channel.close();
            }
            // Сохранение коллекции перед выключением
            fileManager.saveToFile(fileName);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при остановке сервера", e);
        }
    }

    /**
     * Обрабатывает входящий запрос.
     *
     * @param key ключ селектора
     */
    private void handleRequest(SelectionKey key) {
        final DatagramChannel channel = (DatagramChannel) key.channel();
        final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        SocketAddress clientAddress = null;

        try {
            clientAddress = channel.receive(buffer);
            if (clientAddress != null) {
                buffer.flip();
                final byte[] data = new byte[buffer.remaining()];
                buffer.get(data);

                // Десериализация запроса
                final Request request = (Request) NetworkSerializer.deserialize(data);
                logger.info("Получен запрос: " + request);

                //обработка запроса
                final Response response = commandManager.executeCommand(request);

                // Отправка ответа
                final byte[] responseData = NetworkSerializer.serialize(response);
                buffer.clear();
                buffer.put(responseData);
                buffer.flip();
                channel.send(buffer, clientAddress);
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Ошибка при обработке запроса", e);

            if (clientAddress != null) {
                try {
                    final Response errorResponse = Response.error(
                            "Ошибка при обработке запроса: " + e.getMessage(),
                            e.toString(),
                            "Время: " + System.currentTimeMillis()
                    );
                    final byte[] responseData = NetworkSerializer.serialize(errorResponse);
                    buffer.clear();
                    buffer.put(responseData);
                    buffer.flip();
                    channel.send(buffer, clientAddress);
                } catch (IOException ex) {
                    logger.log(Level.SEVERE,
                            "Ошибка при отправке сообщения об ошибке", ex); // вот это вообще прикол
                }
            }
        }
    }
}
