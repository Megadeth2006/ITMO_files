package Manager;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RuntimeManager {
    private CommandManager commandManager;
    private JsonManager jsonManager;
    private ConsoleManager consoleManager;
    private CollectionManager collectionManager;
    public RuntimeManager(ConsoleManager consoleManager, JsonManager jsonManager, CommandManager commandManager, CollectionManager collectionManager){
        this.consoleManager = consoleManager;
        this.jsonManager = jsonManager;
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
    }
    public void InteractiveMode() throws FileNotFoundException {
        Scanner scanner = ScannerManager.getScanner();
        jsonManager.readCollection(collectionManager);
        while (true){
            try{
                String[] userCommand = scanner.nextLine().trim().split(" ");
                commandManager.executeCommand(userCommand[0].toLowerCase(), Arrays.copyOfRange(userCommand, 1, userCommand.length));
            }
            catch (NoSuchElementException e){
                return;
            }
            catch (Exception e){
                consoleManager.printErr(e.getMessage());
            }
        }

    }

}
