package itmo.programming.client.core;

import itmo.programming.client.manager.AskManager;
import itmo.programming.client.manager.AuthManager;
import itmo.programming.client.manager.ConsoleInputManager;
import itmo.programming.client.manager.ConsoleManager;
import itmo.programming.client.manager.RequestSender;
import itmo.programming.client.request.RequestFactory;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.common.utilities.SerializationManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Главный класс клиента.
 */
public class Client {
    private static final Logger logger = Logger.getLogger(Client.class.getName());
    private static final int BUFFER_SIZE = 65536;
    private static final int TIMEOUT = 5000; // 5 seconds timeout
    private static final int SMALL_TIMEOUT = 100;
    private final String host;
    private final int port;
    private DatagramPacket packet;
    private DatagramSocket socket;
    private InetAddress serverAddress;
    private final String clientId = "client-" + System.currentTimeMillis();


    /**
     * Создает новый экземпляр клиента.
     *
     * @param host хост
     * @param port порт сервера
     */
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }


    /**
     * Запускает клиент.
     */
    public void start() {
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(TIMEOUT);
            serverAddress = InetAddress.getByName(host);
            final String clientId = this.clientId;
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            final AskManager askManager = new AskManager(reader);
            final RequestSender requestSender = new RequestSender(this);
            final ConsoleInputManager inputManager = new ConsoleInputManager();
            final AuthManager authManager = new AuthManager();
            final RequestFactory requestFactory = new RequestFactory(askManager,
                    requestSender, authManager);
            final ConsoleManager consoleManager = new ConsoleManager(requestFactory,
                    requestSender);
            // Запуск интерактивного режима
            consoleManager.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при запуске клиента", e);
        } finally {
            stop();
        }
    }

    /**
     * Останавливает клиент.
     */
    public void stop() {
        System.exit(0);
    }

    /**
     * Отправляет запрос серверу и получает ответ.
     *
     * @param request запрос серверу
     * @return ответ сервера
     * @throws IOException при ошибке ввода/вывода
     */
    public Response sendRequest(Request request) throws IOException {


        // Сериализация и отправка запроса
        final byte[] data = SerializationManager.serialize(request);

        final DatagramPacket sendPacket = new DatagramPacket(data,
                data.length, serverAddress, port);
        socket.send(sendPacket);

        // ожидание ответа
        final byte[] receiveBuffer = new byte[BUFFER_SIZE];
        final DatagramPacket receivePacket = new DatagramPacket(receiveBuffer,
                receiveBuffer.length);
        final long startTime = System.currentTimeMillis();

        while (true) {
            try {
                socket.setSoTimeout(SMALL_TIMEOUT);
                socket.receive(receivePacket); // раз в 0,1 секунды будем запрашивать данные
                break;
            } catch (SocketTimeoutException e) {
                if (System.currentTimeMillis() - startTime > TIMEOUT) {
                    throw new IOException("Превышено время ожидания ответа от сервера!");
                }
            }
        }
        // Десериализация ответа
        final byte[] responseData = new byte[receivePacket.getLength()];
        System.arraycopy(receiveBuffer, 0, responseData, 0, receivePacket.getLength());
        try {
            return (Response) SerializationManager.deserialize(responseData);
        } catch (ClassNotFoundException e) {
            throw new IOException("Ошибка при десериализации ответа", e);
        }



    }
}
