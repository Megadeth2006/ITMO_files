package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.SpaceMarine;
import itmo.programming.model.form.SpaceMarineForm;

public class UpdateIdCommand implements CommandInterface {
    private ConsoleManager console;
    private CollectionManager collection;

    public UpdateIdCommand(ConsoleManager console, CollectionManager collection) {
        this.console = console;
        this.collection = collection;
    }

    @Override
    public int execute(String[] args) {
        if (args.length == 0) {
            console.printErr("Не указаны аргументы");
            return 1;
        }
        try {
            int id = Integer.parseInt(args[0]);
            if (collection.findById(id) == null) {
                console.printErr("Элемента с таким индексом не существует");
            }
            SpaceMarine spaceMarine = new SpaceMarineForm(console).updater(id);
            collection.updateById(id, spaceMarine);
            console.print("Значение элемента коллекции по id: " + args[0] + " успешно обновлено");
            return 0;
        } catch (NumberFormatException e) {
            console.printErr("Введите число в качестве id");
            return 2;
        }

    }

    @Override
    public String toString() {
        return "  : обновить значение элемента коллекции, id которого равен заданному";
    }
}
