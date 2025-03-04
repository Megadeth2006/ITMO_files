package itmo.programming.command;

/**
 * Интерфейс команд.
 */
public interface CommandInterface {
    /**
     * Исполнение команды.
     *
     * @param args аргументы.
     */
    int execute(String[] args);
}
