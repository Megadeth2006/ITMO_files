package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.SpaceMarine;
import itmo.programming.model.form.SpaceMarineForm;

/**
 * Команда "add_if_max".
 * Описание команды: добавить новый элемент в коллекцию,
 * если его значение превышает значение наибольшего элемента этой коллекции.
 */
public class AddIfMaxCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    public AddIfMaxCommand(ConsoleManager console, CollectionManager collection) {
        this.console = console;
        this.collection = collection;
    }

    /**
     * Выполнение команды.
     *
     * @param args аргументы
     */
    @Override
    public int execute(String[] args) {
        if (args.length != 0) {
            console.printErr("Команда не принимает аргументы");
            return 1;
        }
        SpaceMarine spaceMarine = new SpaceMarineForm(console).build();
        if (collection.addIfMax(spaceMarine)) {
            console.print("Элемент добавлен в коллекцию");
        } else {
            console.print("Элемент не добавлен в коллекцию");
        }
        return 0;
    }

    @Override
    public String toString() {
        return " : добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
