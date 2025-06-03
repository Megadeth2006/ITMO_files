package itmo.programming.server;

import itmo.programming.server.core.Server;


/**
 * Главный класс сервера.
 */
public class Main {
    private static final int DEFAULT_PORT = 6789;

    public static void main(String[] args)  {
        final int port = DEFAULT_PORT;
        String fileName = "";
        if (args.length > 0) {
            fileName = args[0];
        }

        final Server server = new Server(port, fileName);
        server.start();

    }
}
