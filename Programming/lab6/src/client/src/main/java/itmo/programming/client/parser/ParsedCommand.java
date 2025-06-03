package itmo.programming.client.parser;

/**
 * Класс, который хранит поля для распарсенной команды в строке.
 */
public class ParsedCommand {
    private String command;
    private String[] arguments;

    /**
     * Конструктор.
     *
     * @param command имя команды.
     * @param arguments аргументы
     */
    public ParsedCommand(String command, String[] arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Получить имя команды.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Получить аргументы команды.
     */
    public String[] getArguments() {
        return arguments;
    }

}
