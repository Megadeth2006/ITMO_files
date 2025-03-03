package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

public class InfoCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    public InfoCommand(ConsoleManager console, CollectionManager collection) {
        this.console = console;
        this.collection = collection;
    }


    @Override
    public int execute(String[] args) {
        if (args.length != 0) {
            console.printErr("Команда не принимает аргументы");
            return 1;
        }
        console.println("Дата инициализации коллекции " + CollectionManager.initTime);
        var currentCollection = collection.getCollection();
        console.println("Тип коллекции: " + currentCollection.getClass().getName());
        console.println("Размер коллекции " + currentCollection.size());
        return 0;
    }

    @Override
    public String toString() {
        return ": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
