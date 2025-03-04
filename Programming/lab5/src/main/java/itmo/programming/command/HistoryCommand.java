package itmo.programming.command;

import itmo.programming.manager.CommandManager;
import itmo.programming.manager.ConsoleManager;
import java.util.ArrayList;

/**
 * Команда history.
 * Описание команды: вывести последние 15 команд (без их аргументов).
 */
public class HistoryCommand implements CommandInterface {
    ConsoleManager console;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     */
    public HistoryCommand(ConsoleManager console) {
        this.console = console;
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
            return 1;
        }
        ArrayList<String> usedCommands = new ArrayList<>();
        usedCommands = CommandManager.getUsedCommands();

        int c = 0;
        final int commandConst = 15;
        if (usedCommands.size() >= commandConst) {
            c = commandConst;
        } else {
            c = usedCommands.size();

        }

        for (String command : usedCommands) {
            if (c > 0) {
                console.println(command);
                c -= 1;
                if (c == 0) {
                    console.println("Показаны до 15 последних использованных команд");
                }
            } else {
                console.printWarning("Нечего показывать");
                return 0;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return " : вывести последние 15 команд (без их аргументов)";
    }
}
