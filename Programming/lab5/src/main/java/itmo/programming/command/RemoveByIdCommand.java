package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

/**
 * Команда remove_by_id.
 * Описание команды: удалить элемент из коллекции по его id.
 */
public class RemoveByIdCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     *
     * @param collection объект менеджера коллекции.
     */
    public RemoveByIdCommand(ConsoleManager console, CollectionManager collection) {
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
        if (args.length == 0) {
            console.printErr("Отсутствуют аргументы");
            return 1;
        }
        final int id = Integer.parseInt(args[0]);
        collection.removeById(id);
        console.println("Элемент по индексу " + id + " успешно удален");
        return 0;
    }

    @Override
    public String toString() {
        return " : удалить элемент из коллекции по его id";
    }
}
