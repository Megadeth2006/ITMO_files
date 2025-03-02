package Command;

import Manager.CollectionManager;
import Manager.ConsoleManager;
import Model.SpaceMarine;

public class ShowCommand implements CommandInterface{
    ConsoleManager console;
    CollectionManager collection;
    public ShowCommand(ConsoleManager console, CollectionManager collection){
        this.console = console;
        this.collection = collection;
    }

    @Override
    public int execute(String[] args) {
        if (args.length != 0){
            console.printErr("Команда не принимает аргументы");
            return 0;
        }
        for (SpaceMarine object: collection.getCollection()){
            console.println(object);
        }
        return 1;
    }
}
