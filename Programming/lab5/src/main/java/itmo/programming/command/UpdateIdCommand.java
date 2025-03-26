package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.SpaceMarine;
import itmo.programming.model.make.SpaceMarineMake;

/**
 * Команда update.
 * Описание команды: обновить значение элемента коллекции, id которого равен заданному.
 */
public class UpdateIdCommand implements CommandInterface {
    private ConsoleManager console;
    private CollectionManager collection;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     *
     * @param collection объект менеджера коллекции.
     */
    public UpdateIdCommand(ConsoleManager console, CollectionManager collection) {
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
            console.printErr("Не указаны аргументы");
            return 1;
        }
        try {
            final int id = Integer.parseInt(args[0]);
            if (collection.findById(id) == null) {
                console.printErr("Элемента с таким индексом не существует");
                return 1;
            } else {
                final SpaceMarine spaceMarine = new SpaceMarineMake(console).update(id);
                collection.updateById(id, spaceMarine);
                console.println("Значение элемента коллекции по id: "
                        + args[0] + " успешно обновлено");
                return 0;
            }




        } catch (NumberFormatException e) {
            console.printErr("Введите число в качестве id");
            return 1;
        }

    }

    @Override
    public String toString() {
        return "  : обновить значение элемента коллекции, id которого равен заданному";
    }
}
