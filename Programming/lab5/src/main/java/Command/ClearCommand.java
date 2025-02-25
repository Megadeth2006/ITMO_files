package Command;

import Manager.CollectionManager;
import Manager.ConsoleManager;

// TODO: написать логику
public class ClearCommand implements CommandInterface{
    ConsoleManager console;
    CollectionManager collectionManager;
    public ClearCommand(ConsoleManager console, CollectionManager collectionManager){
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public int execute(String[] args) {
        if (args.length != 0){
            console.printErr("Команда не принимает аргументы");
            return 1;
        }
        collectionManager.clear();
        console.println("Коллекция очищена!");
        return 0;
    }
}
