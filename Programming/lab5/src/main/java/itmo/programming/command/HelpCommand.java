package itmo.programming.command;

import itmo.programming.manager.CommandManager;
import itmo.programming.manager.ConsoleManager;

public class HelpCommand implements CommandInterface {
    ConsoleManager console;
    CommandManager commandManager;

    public HelpCommand(ConsoleManager console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;

    }

    @Override
    public int execute(String[] args) {
        if (args.length != 0) {
            console.printErr("Команда не принимает аргументы!");
            return 1;
        }
        console.println("Доступные команды:");
        commandManager.getCommands().forEach((name, command) -> console.println(name + command.toString()));
        return 0;
    }

    @Override
    public String toString() {
        return ": вывести справку по доступным командам";
    }
}
