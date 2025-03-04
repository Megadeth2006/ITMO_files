package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.SpaceMarine;
import itmo.programming.model.form.SpaceMarineForm;


/**
 * Команда "add".
 * Описание команды: добавить новый элемент в коллекцию.
 */
public class AddCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     *
     * @param collection объект менеджера коллекции.
     */
    public AddCommand(ConsoleManager console, CollectionManager collection) {
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
        final SpaceMarine spaceMarine = new SpaceMarineForm(console).build();
        collection.add(spaceMarine);
        console.println("Элемент добавлен в коллекцию");
        return 0;
    }

    @Override
    public String toString() {
        return ": добавить новый элемент в коллекцию";
    }
}
