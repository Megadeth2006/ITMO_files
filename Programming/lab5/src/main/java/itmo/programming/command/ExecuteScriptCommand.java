package itmo.programming.command;

import itmo.programming.manager.*;

import java.io.File;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

// TODO: написать логику
public class ExecuteScriptCommand implements CommandInterface {
    ConsoleManager console;
    CommandManager commandManager;
    JsonManager jsonManager;

    public ExecuteScriptCommand(ConsoleManager console, CommandManager commandManager, JsonManager jsonManager) {
        this.console = console;
        this.commandManager = commandManager;
        this.jsonManager = jsonManager;
    }

    @Override
    public int execute(String[] args) {
        if (args.length == 0) {
            console.printErr("Не хватает названия файла в качестве аргумента");
            return 1;
        }
        String prefix = "src\\main\\java\\itmo\\programming\\";
        String filename = prefix + args[0];
        File file = new File(filename);


        if (!jsonManager.canReadFile(file, console)) {
            return 2;
        }
        try {
            ScriptManager.addToStack(filename);
            Scanner scannerManager;
            while ((scannerManager = ScriptManager.getLastScanner()) != null) {
                ScannerManager.setScanner(scannerManager);
                String line = scannerManager.nextLine();
                String[] command = line.trim().split(" ");
                if (command[0].equalsIgnoreCase("execute_script") && ScriptManager.isRecursive(prefix + command[1])) {
                    console.printErr("Найдена рекурсия! Повторно вызывается файл: " + new File(prefix + command[1]).getAbsolutePath());
                    continue;
                }
                console.print("Выполнение команды " + command[0] + "(" + filename + ")" + ":");
                if (commandManager.getCommands().get(command[0]) != null) {
                    var statusCode = commandManager.executeCommand(command[0], Arrays.copyOfRange(command, 1, command.length));
                    if (statusCode != 0) {
                        ScriptManager.removeFromStack();
                        return statusCode;
                    }
                } else {
                    console.printErr("Такой команды не существует!");
                    return 3;
                }
            }
            ScriptManager.removeFromStack();
            ScannerManager.setScanner(new Scanner(System.in));
        } catch (NoSuchElementException e) {
            return 4;
        } catch (Exception e) {
            console.printErr(e.getMessage());
            return 5;
        }
        return 0;
    }

    @Override
    public String toString() {
        return " : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
