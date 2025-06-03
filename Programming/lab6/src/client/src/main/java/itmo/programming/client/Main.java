package itmo.programming.client;

import itmo.programming.client.core.Client;

/**
 * Главный класс приложения клиента.
 */
public class Main {
    private static final int DEFAULT_PORT = 6789;

    /**
     * Точка входа в клиентское приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        try {
            final String host = "localhost";
            final int port = DEFAULT_PORT;
            final Client client = new Client(host, port);
            client.start();
        } catch (NumberFormatException e) {
            System.out.println("Порт должен быть числом");
        }
    }
}
