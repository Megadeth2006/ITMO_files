package itmo.programming.server.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Менеджер консоли сервера.
 */
public class ConsoleManager {
    private final BufferedReader reader;

    /**
     * Конструктор менеджера консоли сервера.
     */
    public ConsoleManager() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Метод для чтения данных с консоли.
     *
     * @return String
     */
    public String readInput() throws IOException {

        final String input = reader.readLine();
        if (input == null) {
            return null;
        }
        if (input.trim().isEmpty()) {
            return null;
        }

        return input;
    }
}
