package itmo.programming;

import itmo.programming.command.*;
import itmo.programming.manager.*;

import java.io.FileNotFoundException;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {

        ConsoleManager console = new ConsoleManager();
        CollectionManager collection = new CollectionManager();
        String fileName = "";
        console.print("Введите имя файла:");
        fileName = console.readln();
        JsonManager jsonManager = new JsonManager("src\\main\\java\\itmo\\programming\\"+fileName, console, collection);
        CommandManager commandManager = new CommandManager(console);
        IdManager.setCollectionManager(collection);

        commandManager.addCommand("help", new HelpCommand(console, commandManager));
        commandManager.addCommand("info", new InfoCommand(console, collection));
        commandManager.addCommand("show", new ShowCommand(console, collection));
        commandManager.addCommand("add", new AddCommand(console, collection));
        commandManager.addCommand("update", new UpdateIdCommand(console, collection));
        commandManager.addCommand("remove_by_id", new RemoveByIdCommand(console, collection));
        commandManager.addCommand("clear", new ClearCommand(console, collection));
        commandManager.addCommand("save", new SaveCommand(console, jsonManager));
        // execute script command
        commandManager.addCommand("exit", new ExitCommand(console));
        commandManager.addCommand("history", new HistoryCommand(console));
        commandManager.addCommand("filter_by_achievements", new FilterByAchievementsCommand(console, collection));
        commandManager.addCommand("sum_of_health", new SumOfHealthCommand(console, collection));
        commandManager.addCommand("add_if_max", new AddIfMaxCommand(console, collection));
        commandManager.addCommand("add_if_min", new AddIfMinCommand(console, collection));
        commandManager.addCommand("execute_script", new ExecuteScriptCommand(console, commandManager, jsonManager));
        new RuntimeManager(console, jsonManager, commandManager, collection).InteractiveMode();

    }

}




