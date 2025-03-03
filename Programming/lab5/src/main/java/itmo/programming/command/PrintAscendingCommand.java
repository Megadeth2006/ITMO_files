package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

public class PrintAscendingCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    public PrintAscendingCommand(ConsoleManager console, CollectionManager collection) {
        this.console = console;
        this.collection = collection;
    }

    @Override
    public int execute(String[] args) {
        if (args.length != 0) {
            console.printErr("Команда не принимает аргументы");
            return 1;
        }

        for (var object : collection.getCollection()) {
            console.print(object);
        }
        return 0;
    }

    @Override
    public String toString() {
        return " : вывести элементы коллекции в порядке возрастания";
    }
}
