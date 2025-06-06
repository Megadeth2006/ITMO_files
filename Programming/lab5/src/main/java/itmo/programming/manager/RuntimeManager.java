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
    private boolean shouldSave = true;

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
    public void interactiveMode() {


        final Scanner scanner = ScannerManager.getScanner();
        fileManager.readCollection(collection);
        console.println("Напишите help, если не знаете написание команд"
                + " (либо если не знаете что вообще написать)");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (shouldSave) {
                try {
                    if (fileManager.writeCollection()) {
                        console.println("Данные успешно сохранены перед выходом.");
                    } else {
                        console.printWarning("Данные не сохранены (Файл не загружен)");
                    }
                } catch (FileNotFoundException e) {
                    console.printErr("Ошибка при сохранении данных");
                }
            }

        }));
        while (true) {
            try {
                final String[] userCommand = scanner.nextLine().trim().split(" ");
                if ("exit".equalsIgnoreCase(userCommand[0])) {
                    shouldSave = false;
                    commandManager.executeCommand(userCommand[0].toLowerCase(),
                            Arrays.copyOfRange(userCommand, 1, userCommand.length));
                    System.exit(0);
                } else {
                    commandManager.executeCommand(userCommand[0].toLowerCase(),
                            Arrays.copyOfRange(userCommand, 1, userCommand.length));
                }


            } catch (NoSuchElementException e) {
                System.exit(0);
            }
        }

    }

}
