package Command;

import Manager.CollectionManager;
import Manager.ConsoleManager;
import Manager.JsonManager;

import java.io.FileNotFoundException;

// TODO: написать логику
public class SaveCommand implements CommandInterface{
    ConsoleManager console;
    CollectionManager collectionManager;
    JsonManager jsonManager;

    public SaveCommand(ConsoleManager console, CollectionManager collectionManager,JsonManager jsonManager){
        this.console = console;
        this.collectionManager = collectionManager;
        this.jsonManager = jsonManager;
    }


    @Override
    public int execute(String[] args) {
        try{
            jsonManager.writeCollection(collectionManager.getCollection());
            return 0;
        }
        catch (FileNotFoundException e){
            console.printErr("Нет файла");
            return 1;
        }

    }
}
