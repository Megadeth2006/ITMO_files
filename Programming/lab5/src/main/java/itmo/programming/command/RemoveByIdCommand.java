package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

public class RemoveByIdCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    public RemoveByIdCommand(ConsoleManager console, CollectionManager collection) {
        this.console = console;
        this.collection = collection;
    }

    @Override
    public int execute(String[] args) {
        if (args.length == 0) {
            console.printErr("Отсутствуют аргументы");
            return 1;
        }
        int id = Integer.parseInt(args[0]);
        collection.removeById(id);
        console.print("Элемент по индексу " + id + " успешно удален");
        return 0;
    }

    @Override
    public String toString() {
        return " : удалить элемент из коллекции по его id";
    }
}
