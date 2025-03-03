package itmo.programming.command;

/**
 * Интерфейс команд
 */
public interface CommandInterface {
    /**
     * Выполнение команды
     *
     * @param args аргументы
     */
    int execute(String[] args);
}
