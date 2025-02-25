package Command;

import Manager.CollectionManager;
import Manager.ConsoleManager;
import Model.SpaceMarine;
import Model.form.SpaceMarineForm;


// TODO: написать логику
public class AddCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collectionManager;
    public AddCommand(ConsoleManager console, CollectionManager collectionManager){
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public int execute(String[] args) {
        if (args.length != 0){
            console.printErr("Команда не принимает аргументы");
            return 1;
        }
        SpaceMarine spaceMarine = new SpaceMarineForm(console).build();
        collectionManager.add(spaceMarine);
        console.println(spaceMarine);
        return 0;
    }

    @Override
    public String toString() {
        return ": добавить новый элемент в коллекцию";
    }
}
