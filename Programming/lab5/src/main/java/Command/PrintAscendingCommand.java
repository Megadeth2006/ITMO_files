package Command;

import Manager.CollectionManager;
import Manager.ConsoleManager;

public class PrintAscendingCommand implements CommandInterface{
    ConsoleManager console;
    CollectionManager collection;
    public PrintAscendingCommand(ConsoleManager console, CollectionManager collection){
        this.console = console;
        this.collection = collection;
    }

    @Override
    public int execute(String[] args) {
        return 0;
    }

    @Override
    public String toString() {
        return " : вывести элементы коллекции в порядке возрастания";
    }
}
