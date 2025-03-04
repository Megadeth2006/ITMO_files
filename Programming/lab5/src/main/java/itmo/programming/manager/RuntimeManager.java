package itmo.programming.manager;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для реализации интерактивного режима работы программы.
 */
public class RuntimeManager {
    private CommandManager commandManager;
    private FileManager fileManager;
    private ConsoleManager console;
    private CollectionManager collection;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     *
     * @param fileManager объект файлового менеджера.
     *
     * @param commandManager объект командного менеджера.
     *
     * @param collection объект менеджера коллекции.
     */
    public RuntimeManager(ConsoleManager console,
                          FileManager fileManager, CommandManager commandManager,
                          CollectionManager collection) {
        this.console = console;
        this.fileManager = fileManager;
        this.commandManager = commandManager;
        this.collection = collection;
    }

    /**
     * Метод, реализующий интерактивный режим.
     */
    public void interactiveMode() throws FileNotFoundException {
        final Scanner scanner = ScannerManager.getScanner();
        fileManager.readCollection(collection);
        console.println("Напишите help, если не знаете написание команд"
                + " (либо если не знаете что вообще написать)");
        while (true) {
            try {
                final String[] userCommand = scanner.nextLine().trim().split(" ");
                commandManager.executeCommand(userCommand[0].toLowerCase(),
                        Arrays.copyOfRange(userCommand, 1, userCommand.length));
            } catch (NoSuchElementException e) {
                return;
            }
        }

    }

}
