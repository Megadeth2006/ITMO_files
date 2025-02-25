import Manager.*;
import Command.*;

import java.io.FileNotFoundException;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        ConsoleManager console = new ConsoleManager();
        JsonManager jsonManager = new JsonManager("C:\\Users\\danie\\OneDrive\\Desktop\\prog5\\src\\main\\java\\1234.json", console);
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager(console);

        commandManager.addCommand("add", new AddCommand(console, collectionManager));

        commandManager.addCommand("clear", new ClearCommand(console, collectionManager));
        commandManager.addCommand("exit", new ExitCommand(console));
        commandManager.addCommand("save", new SaveCommand(console, collectionManager, jsonManager));
        commandManager.addCommand("help", new HelpCommand(console, commandManager));
        IdManager.setCollectionManager(collectionManager);
        new RuntimeManager(console, jsonManager, commandManager, collectionManager).InteractiveMode();

    }

}




