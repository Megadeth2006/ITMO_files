package Command;

import Manager.CollectionManager;
import Manager.ConsoleManager;
import Model.SpaceMarine;
import Model.form.SpaceMarineForm;


public class AddIfMinCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;
    public AddIfMinCommand(ConsoleManager console, CollectionManager collection){
        this.console = console;
        this.collection = collection;
    }
    @Override
    public int execute(String[] args) {
        if (args.length != 0){
            console.printErr("Команда не принимает аргументы");
            return 1;
        }
        SpaceMarine spaceMarine = new SpaceMarineForm(console).build();
        if (collection.addIfMin(spaceMarine)){
            console.print("Элемент добавлен в коллекцию");
        }
        else{
            console.print("Элемент не добавлен в коллекцию");
        }
        return 0;
    }

    @Override
    public String toString() {
        return " : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
