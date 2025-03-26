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
        try {
            final int id = Integer.parseInt(args[0]);
            if (collection.removeById(id)) {
                console.println("Элемент по индексу " + id + " успешно удален");
                return 0;
            } else {
                console.printErr("Элемента с индексом " + id + " не существует!");
                return 1;
            }

        } catch (NumberFormatException e) {
            console.printErr("Введите число!");
            return 1;
        }


    }

    @Override
    public String toString() {
        return " : удалить элемент из коллекции по его id";
    }
}
