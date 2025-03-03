package itmo.programming.command;

import itmo.programming.manager.CommandManager;
import itmo.programming.manager.ConsoleManager;

import java.util.ArrayList;

public class HistoryCommand implements CommandInterface {
    ConsoleManager console;

    public HistoryCommand(ConsoleManager console) {
        this.console = console;
    }

    @Override
    public int execute(String[] args) {
        if (args.length != 0) {
            console.printErr("Команда не принимает аргументы");
            return 1;
        }
        ArrayList<String> usedCommands = new ArrayList<>();
        usedCommands = CommandManager.getUsedCommands();

        int c = 0;

        if (usedCommands.size() >= 15) {
            c = 15;
        } else {
            c = usedCommands.size();

        }

        for (String command : usedCommands) {
            if (c > 0) {
                console.print(command);
                c -= 1;
                if (c == 0) {
                    console.print("Показаны до 15 последних использованных команд");
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
