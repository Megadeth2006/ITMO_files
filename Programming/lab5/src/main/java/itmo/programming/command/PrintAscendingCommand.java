package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

/**
 * Команда print_ascending.
 * Описание команды: вывести элементы коллекции в порядке возрастания.
 */
public class PrintAscendingCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     *
     * @param collection объект менеджера коллекции.
     */
    public PrintAscendingCommand(ConsoleManager console, CollectionManager collection) {
        this.console = console;
        this.collection = collection;
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

        for (var object : collection.printAscending()) {
            console.print(object);
        }
        return 0;
    }

    @Override
    public String toString() {
        return " : вывести элементы коллекции в порядке возрастания";
    }
}
