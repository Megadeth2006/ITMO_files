package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

/**
 * Команда show.
 * Описание команды: вывести
 * в стандартный поток вывода все элементы коллекции в строковом представлении.
 */
public class ShowCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     *
     * @param collection объект менеджера коллекции.
     */
    public ShowCommand(ConsoleManager console, CollectionManager collection) {
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
        console.print(collection.getCollectionAsString());
        return 0;
    }

    @Override
    public String toString() {
        return ": вывести в стандартный поток вывода"
                + " все элементы коллекции в строковом представлении";
    }
}
