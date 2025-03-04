package itmo.programming;

import itmo.programming.command.AddCommand;
import itmo.programming.command.AddIfMaxCommand;
import itmo.programming.command.AddIfMinCommand;
import itmo.programming.command.ClearCommand;
import itmo.programming.command.ExecuteScriptCommand;
import itmo.programming.command.ExitCommand;
import itmo.programming.command.FilterByAchievementsCommand;
import itmo.programming.command.HelpCommand;
import itmo.programming.command.HistoryCommand;
import itmo.programming.command.InfoCommand;
import itmo.programming.command.PrintAscendingCommand;
import itmo.programming.command.RemoveByIdCommand;
import itmo.programming.command.SaveCommand;
import itmo.programming.command.ShowCommand;
import itmo.programming.command.SumOfHealthCommand;
import itmo.programming.command.UpdateIdCommand;
import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.CommandManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.manager.FileManager;
import itmo.programming.manager.IdManager;
import itmo.programming.manager.RuntimeManager;
import java.io.FileNotFoundException;

/**
 * Главный класс.
 */

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        final ConsoleManager console = new ConsoleManager();
        final CollectionManager collection = new CollectionManager();
        String fileName = "";
        console.println("Введите имя файла:");
        fileName = console.readln();
        final FileManager fileManager = new FileManager("src\\main\\java\\itmo\\programming\\"
                + fileName, console, collection);
        final CommandManager commandManager = new CommandManager(console);
        IdManager.setCollectionManager(collection);

        commandManager.addCommand("help",
                new HelpCommand(console, commandManager));

        commandManager.addCommand("info",
                new InfoCommand(console, collection));

        commandManager.addCommand("show",
                new ShowCommand(console, collection));

        commandManager.addCommand("add",
                new AddCommand(console, collection));

        commandManager.addCommand("update",
                new UpdateIdCommand(console, collection));

        commandManager.addCommand("remove_by_id",
                new RemoveByIdCommand(console, collection));

        commandManager.addCommand("clear",
                new ClearCommand(console, collection));

        commandManager.addCommand("save",
                new SaveCommand(console, fileManager));

        commandManager.addCommand("execute_script",
                new ExecuteScriptCommand(console, commandManager, fileManager));

        commandManager.addCommand("exit",
                new ExitCommand(console));

        commandManager.addCommand("history",
                new HistoryCommand(console));

        commandManager.addCommand("filter_by_achievements",
                new FilterByAchievementsCommand(console, collection));

        commandManager.addCommand("sum_of_health",
                new SumOfHealthCommand(console, collection));

        commandManager.addCommand("add_if_max",
                new AddIfMaxCommand(console, collection));

        commandManager.addCommand("add_if_min",
                new AddIfMinCommand(console, collection));

        commandManager.addCommand("print_ascending",
                new PrintAscendingCommand(console, collection));

        new RuntimeManager(console, fileManager,
                commandManager, collection).interactiveMode();

    }

}




