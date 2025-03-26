package itmo.programming.command;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.manager.FileManager;
import java.io.FileNotFoundException;

/**
 * Команда save.
 * Описание команды: сохранить коллекцию в файл.
 */
public class SaveCommand implements CommandInterface {
    ConsoleManager console;
    FileManager fileManager;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     *
     * @param fileManager объект менеджера Json.
     */
    public SaveCommand(ConsoleManager console, FileManager fileManager) {
        this.console = console;
        this.fileManager = fileManager;
    }

    /**
     * Исполнение команды.
     *
     * @param args аргументы.
     */
    @Override
    public int execute(String[] args) {
        if (args.length != 0) {
            console.printErr("Команда не принимает аргументы");
            return 2;
        }
        try {
            fileManager.writeCollection();
            return 0;
        } catch (FileNotFoundException e) {
            console.printErr("Нет файла");
            return 1;
        }

    }

    @Override
    public String toString() {
        return ": сохранить коллекцию в файл";
    }
}
