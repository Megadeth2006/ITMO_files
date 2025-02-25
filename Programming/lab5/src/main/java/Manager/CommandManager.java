package Manager;

import Command.CommandInterface;

import java.util.HashMap;

public class CommandManager {
    HashMap<String, CommandInterface> commands = new HashMap<>();
    ConsoleManager console;
    public CommandManager(ConsoleManager console){
        this.console = console;
    }
    public void addCommand(String commandName, CommandInterface command){
        commands.put(commandName, command);
    }
    public int executeCommand(String commandName, String[] args){
        CommandInterface command = commands.get(commandName);
        if (command != null){
            try{
                return command.execute(args);
            }
            catch (Exception e){
                console.printErr(e.getClass().getName());
                return -1;
            }
        }
        console.print("Команды не существует");
        return -1;
    }
    public HashMap<String, CommandInterface> getCommands(){
        return this.commands;
    }
}
