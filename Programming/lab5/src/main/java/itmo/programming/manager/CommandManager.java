package itmo.programming.manager;

import itmo.programming.command.CommandInterface;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс для работы с командами.
 */
public class CommandManager {
    /**
     * Список использованных команд в порядке времени использования.
     */
    private static ArrayList<String> usedCommands = new ArrayList<>();
    /**
     * Хеш-словарь: Название команды - Объект команды, реализующий интерфейс.
     */
    HashMap<String, CommandInterface> commands = new HashMap<>();

    ConsoleManager console;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     */
    public CommandManager(ConsoleManager console) {
        this.console = console;
    }

    /**
     * Получить список использованных команд.
     */
    public static ArrayList<String> getUsedCommands() {
        return usedCommands;
    }

    /**
     * Изменить список использованных команд.
     *
     * @param usedCommands использованные команды
     */
    public static void setUsedCommands(ArrayList<String> usedCommands) {
        CommandManager.usedCommands = usedCommands;
    }

    /**
     * Добавить команду в хеш-словарь с командами.
     *
     * @param commandName название команды.
     *
     * @param command Объект команды.
     */
    public void addCommand(String commandName, CommandInterface command) {
        commands.put(commandName, command);
    }

    /**
     * Исполнение команды и получение ответа.
     *
     * @param commandName название команды.
     *
     * @param args Аргументы.
     */
    public int executeCommand(String commandName, String[] args) {
        final CommandInterface command = commands.get(commandName);
        if (command != null) {
            try {
                final int response = command.execute(args);
                final ArrayList<String> usedCommands = getUsedCommands();
                usedCommands.add(commandName);
                setUsedCommands(usedCommands);
                return response;
            } catch (NullPointerException e) {
                console.printErr("Повторите попытку");
                return -1;
            }
        }
        console.println("Команды не существует");
        return -1;
    }

    /**
     * Получить словарь со всеми командами.
     */
    public HashMap<String, CommandInterface> getCommands() {
        return this.commands;
    }
}
