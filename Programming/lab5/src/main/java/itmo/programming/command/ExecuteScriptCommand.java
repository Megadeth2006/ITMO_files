package itmo.programming.command;

import itmo.programming.manager.CommandManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.manager.FileManager;
import itmo.programming.manager.ScannerManager;
import itmo.programming.manager.ScriptManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Команда execute_script.
 * Описание команды: считать и исполнить скрипт из указанного файла.
 * В скрипте содержатся команды в таком же виде,
 * в котором их вводит пользователь в интерактивном режиме.
 */
public class ExecuteScriptCommand implements CommandInterface {
    ConsoleManager console;
    CommandManager commandManager;
    FileManager fileManager;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     *
     * @param commandManager объект менеджера команд.
     *
     * @param fileManager объект менеджера Json.
     */
    public ExecuteScriptCommand(ConsoleManager console,
                                CommandManager commandManager, FileManager fileManager) {
        this.console = console;
        this.commandManager = commandManager;
        this.fileManager = fileManager;
    }

    /**
     * Исполнение команды.
     *
     * @param args аргументы.
     */
    @Override
    public int execute(String[] args) {
        if (args.length == 0) {
            console.printErr("Не хватает названия файла в качестве аргумента");
            return 1;
        }

        final String filename = args[0];
        final File file = new File(filename);


        if (!fileManager.canReadFile(file, console)) {
            return 1;
        }
        try {
            ScriptManager.addToStack(filename);
            Scanner scannerManager;
            while ((scannerManager = ScriptManager.getLastScanner()) != null) {
                ScannerManager.setScanner(scannerManager);
                final String line = scannerManager.nextLine();
                final String[] command = line.trim().split(" ");
                if (command[0].equalsIgnoreCase("execute_script")
                        && ScriptManager.isRecursive(command[1])) {
                    console.printErr("Найдена рекурсия! Повторно вызывается файл: " + new File(
                            command[1]).getAbsolutePath());
                    continue;
                }
                if (!(Objects.equals(command[0], ""))) {
                    console.println("Выполнение команды "
                            + command[0] + "(" + filename + ")" + ":");
                    if (commandManager.getCommands().get(command[0]) != null) {
                        final var statusCode = commandManager.executeCommand(command[0],
                                Arrays.copyOfRange(command, 1, command.length));
                        if (statusCode != 0) {
                            ScriptManager.removeFromStack();
                            return statusCode;
                        }
                    } else {

                        console.printErr("Такой команды не существует!");
                        return 1;

                    }
                }
            }
            ScriptManager.removeFromStack();
            ScannerManager.setScanner(new Scanner(System.in));
        } catch (NoSuchElementException e) {
            return 1;
        } catch (FileNotFoundException e) {
            console.printErr(e.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return " : считать и исполнить скрипт из указанного файла."
                + " В скрипте содержатся команды "
                + "в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
