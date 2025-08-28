package itmo.programming.server.core;

import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.network.NetworkSerializer;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.database.CollectionDaO;
import itmo.programming.server.database.UserDaO;
import itmo.programming.server.manager.AuthManager;
import itmo.programming.server.manager.CollectionManager;
import itmo.programming.server.manager.CommandManager;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Главный класс сервера.
 */
public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());
    private static final int SLEEP_TIME = 1000;
    private static final int BUFFER_SIZE = 65536;
    private final ExecutorService requestProcessorPool = Executors.newFixedThreadPool(10);
    private final ExecutorService responseSenderPool = Executors.newCachedThreadPool();
    private final int port;
    private final Connection connection;
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;
    private final UserDaO userdao;
    private final AuthManager authManager;
    private final CollectionDaO collectiondao;
    private DatagramChannel channel;
    private Selector selector;
    private volatile boolean running;
    private Thread requestReaderThread;

    /**
     * Создает новый экземпляр сервера.
     *
     * @param port номер порта для прослушивания
     */
    public Server(int port, Connection connection) {
        this.port = port;
        this.connection = connection;
        this.collectionManager = new CollectionManager();
        this.userdao = new UserDaO(connection);
        this.authManager = new AuthManager(userdao);
        this.collectiondao = new CollectionDaO(connection);
        this.commandManager = new CommandManager(collectionManager, authManager, collectiondao);
    }

    /**
     * Запускает сервер.
     */
    public void start() {
        try {
            initializeServer();
            startRequestReaderThread();
            logger.info("Сервер запущен на порту " + port);
            
            while (running) {
                Thread.sleep(SLEEP_TIME); // Главный поток ждет
            }
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, "Ошибка в работе сервера", e);
        } finally {
            shutdown();
        }
    }

    private void initializeServer() throws IOException {
        channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress(port));
        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);
        running = true;

        final TreeSet<SpaceMarine> collection = collectiondao.loadAll();
        collectionManager.setCollection(collection);
        logger.info("Коллекция успешно загружена из базы данных в память.");
    }

    private void startRequestReaderThread() {
        requestReaderThread = new Thread(() -> {
            final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            while (running) {
                try {
                    if (selector.select() > 0) {
                        final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                        while (iterator.hasNext()) {
                            final SelectionKey key = iterator.next();
                            iterator.remove();

                            if (key.isReadable()) {
                                handleRequest(buffer);
                            }
                        }
                    }
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Ошибка при чтении запроса", e);
                }
            }
        }, "RequestReader");
        requestReaderThread.start();
    }

    private void handleRequest(ByteBuffer buffer) throws IOException {
        buffer.clear();
        final SocketAddress clientAddress = channel.receive(buffer);
        if (clientAddress != null) {
            buffer.flip();
            final byte[] data = new byte[buffer.remaining()];
            buffer.get(data);

            requestProcessorPool.execute(() -> processRequest(data, clientAddress));
        }
    }

    private void processRequest(byte[] data, SocketAddress clientAddress) {
        try {
            final Request request = (Request) NetworkSerializer.deserialize(data);
            final Response response = commandManager.executeCommand(request);
            sendResponse(response, clientAddress);
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Ошибка при обработке запроса", e);
            final Response errorResponse = Response.error(
                "Ошибка при обработке запроса: " + e.getMessage(),
                e.toString(),
                null,
                "unknown"
            );
            sendResponse(errorResponse, clientAddress);
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);

        }
    }

    private void sendResponse(Response response, SocketAddress clientAddress) {
        responseSenderPool.execute(() -> {
            try {
                final byte[] responseData = NetworkSerializer.serialize(response);
                final ByteBuffer responseBuffer = ByteBuffer.wrap(responseData);
                channel.send(responseBuffer, clientAddress);
            } catch (IOException e) {
                logger.log(Level.WARNING, "Ошибка при отправке ответа", e);
            }
        });
    }

    private void shutdown() {
        running = false;
        requestProcessorPool.shutdown();
        responseSenderPool.shutdown();
        try {
            if (channel != null) {
                channel.close();
            }
            if (selector != null) {
                selector.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (IOException | java.sql.SQLException e) {
            logger.log(Level.WARNING, "Ошибка при закрытии ресурсов", e);
        }
    }
}
