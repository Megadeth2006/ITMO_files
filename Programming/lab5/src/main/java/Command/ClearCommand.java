package Command;

import Manager.CollectionManager;
import Manager.ConsoleManager;


public class ClearCommand implements CommandInterface{
    ConsoleManager console;
    CollectionManager collection;
    public ClearCommand(ConsoleManager console, CollectionManager collection){
        this.console = console;
        this.collection = collection;
    }

    @Override
    public int execute(String[] args) {
        if (args.length != 0){
            console.printErr("Команда не принимает аргументы");
            return 1;
        }
        collection.clear();
        console.println("Коллекция очищена!");
        return 0;
    }

    @Override
    public String toString() {
       return " : очистить коллекцию";
    }
}
