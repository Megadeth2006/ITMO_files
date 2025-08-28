package itmo.programming.client.parser;

/**
 * Класс, который реализует парсинг команды и ее аргументов в строке.
 */
public class CommandParser {

    /**
     * Метод, реализующий парсинг.
     *
     * @param input строка ввода.
     */
    public ParsedCommand parse(String input) {
        final String[] parts = input.trim().split("\\s+", 2);
        final String command = parts[0].toLowerCase();
        final String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];
        return new ParsedCommand(command, args);
    }
}
