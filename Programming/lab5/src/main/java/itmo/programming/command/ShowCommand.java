package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.SpaceMarine;

public class ShowCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    public ShowCommand(ConsoleManager console, CollectionManager collection) {
        this.console = console;
        this.collection = collection;
    }

    @Override
    public int execute(String[] args) {
        if (args.length != 0) {
            console.printErr("Команда не принимает аргументы");
            return 1;
        }
        for (SpaceMarine object : collection.getCollection()) {
            console.println(object);
        }
        return 0;
    }
}
