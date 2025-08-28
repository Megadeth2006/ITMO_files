package itmo.programming.client.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * Менеджер консоли клиента.
 */
public class ConsoleInputManager {
    private static final Logger logger = Logger.getLogger(ConsoleInputManager.class.getName());

    
    private final BufferedReader reader;
    private boolean running;

    /**
     * Создает новый экземпляр менеджера консоли.
     */
    public ConsoleInputManager() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }


    /**
     * Считывает строку из консоли.
     *
     * @return введённая строка
     * @throws IOException при ошибке чтения
     */
    public String readLine() throws IOException {
        System.out.print("> ");
        return reader.readLine();
    }

    /**
     * Закрывает поток чтения.
     *
     * @throws IOException при ошибке закрытия
     */
    public void close() throws IOException {
        reader.close();
    }
}
