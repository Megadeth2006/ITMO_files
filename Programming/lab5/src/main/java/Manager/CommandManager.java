package Manager;

import Command.CommandInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandManager {
    HashMap<String, CommandInterface> commands = new HashMap<>();
    private static ArrayList<String> usedCommands = new ArrayList<>();

    ConsoleManager console;
    public CommandManager(ConsoleManager console){
        this.console = console;
    }

    public static ArrayList<String> getUsedCommands() {
        return usedCommands;
    }

    public static void setUsedCommands(ArrayList<String> usedCommands) {
        CommandManager.usedCommands = usedCommands;
    }

    public void addCommand(String commandName, CommandInterface command){
        commands.put(commandName, command);
    }

    public int executeCommand(String commandName, String[] args){
        CommandInterface command = commands.get(commandName);
        if (command != null){
            try{
                int response = command.execute(args);
                ArrayList<String> usedCommands = getUsedCommands();
                usedCommands.add(commandName);
                setUsedCommands(usedCommands);
                return response;
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
