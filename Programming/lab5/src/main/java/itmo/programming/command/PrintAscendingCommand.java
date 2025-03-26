package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.SpaceMarine;
import java.util.ArrayList;

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
        final ArrayList<SpaceMarine> ascendingArray = collection.getAscending();
        if (ascendingArray == null) {
            console.printWarning("Коллекция пуста!");
            return 0;
        } else {
            for (var object : ascendingArray) {
                console.println(object);
            }
            return 0;
        }
    }

    @Override
    public String toString() {
        return " : вывести элементы коллекции в порядке возрастания";
    }
}
