package Command;

import Manager.CollectionManager;
import Manager.ConsoleManager;
import Model.SpaceMarine;
import Model.form.SpaceMarineForm;

public class UpdateIdCommand implements CommandInterface {
    private ConsoleManager console;
    private CollectionManager collection;
    public UpdateIdCommand(ConsoleManager console, CollectionManager collection){
        this.console = console;
        this.collection = collection;
    }
    @Override
    public int execute(String[] args) {
        if (args.length == 0){
            console.printErr("Не указаны аргументы");
            return 1;
        }
        try {
            int id = Integer.parseInt(args[0]);
            if (collection.findById(id) == null){
                console.printErr("Элемента с таким индексом не существует");
            }
            SpaceMarine spaceMarine = new SpaceMarineForm(console).updater(id);
            collection.updateById(id, spaceMarine);
            console.print("Значение элемента коллекции по id: " + args[0] + " успешно обновлено");
            return 2;
        }catch (NumberFormatException e){
            console.printErr("Введите число в качестве id");
            return 3;
        }

    }

    @Override
    public String toString() {
        return "  : обновить значение элемента коллекции, id которого равен заданному";
    }
}
