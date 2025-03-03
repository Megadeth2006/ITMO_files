package itmo.programming.command;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.manager.JsonManager;

import java.io.FileNotFoundException;


public class SaveCommand implements CommandInterface {
    ConsoleManager console;
    JsonManager jsonManager;

    public SaveCommand(ConsoleManager console, JsonManager jsonManager) {
        this.console = console;
        this.jsonManager = jsonManager;
    }


    @Override
    public int execute(String[] args) {
        if (args.length != 0) {
            console.printErr("Команда не принимает аргументы");
            return 2;
        }
        try {
            jsonManager.writeCollection();
            return 0;
        } catch (FileNotFoundException e) {
            console.printErr("Нет файла");
            return 1;
        }

    }

}
