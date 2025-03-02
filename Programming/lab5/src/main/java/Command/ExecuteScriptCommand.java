package Command;

import Manager.CommandManager;
import Manager.ConsoleManager;

import java.io.File;

// TODO: написать логику
public class ExecuteScriptCommand implements CommandInterface{
    ConsoleManager console;
    CommandManager command;
    public ExecuteScriptCommand(ConsoleManager console, CommandManager command){
        this.console = console;
        this.command = command;
    }
    @Override
    public int execute(String[] args) {
        if (args.length == 0){
            console.printErr("Не хватает названия файла в качестве аргумента");
            return 1;
        }
        String filename = args[0];
        File file = new File(filename);

        return 0;

    }

    @Override
    public String toString() {
        return " : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
