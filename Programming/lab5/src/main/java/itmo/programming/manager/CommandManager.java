package itmo.programming.manager;

import itmo.programming.command.CommandInterface;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс для работы с командами.
 */
public class CommandManager {
    /**
     * Хеш-словарь: Название команды - Объект команды, реализующий интерфейс.
     */
    HashMap<String, CommandInterface> commands = new HashMap<>();

    ConsoleManager console;
    /**
     * Список использованных команд в порядке времени использования.
     */
    private ArrayList<String> usedCommands = new ArrayList<>();


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
    public ArrayList<String> getUsedCommands() {
        return this.usedCommands;
    }

    /**
     * Изменить список использованных команд.
     *
     * @param usedCommands использованные команды
     */
    public  void setUsedCommands(ArrayList<String> usedCommands) {
        this.usedCommands = usedCommands;
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
            final int response = command.execute(args);
            final ArrayList<String> usedCommands = getUsedCommands();
            usedCommands.add(commandName);
            setUsedCommands(usedCommands);
            return response;
        }
        console.println("Команды не существует");
        return 0;
    }

    /**
     * Получить словарь со всеми командами.
     */
    public HashMap<String, CommandInterface> getCommands() {
        return this.commands;
    }
}
