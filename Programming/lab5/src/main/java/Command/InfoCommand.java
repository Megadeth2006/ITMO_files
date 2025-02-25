package Command;

import Manager.CollectionManager;
import Manager.ConsoleManager;

public class InfoCommand implements CommandInterface{
    ConsoleManager console;
    CollectionManager collectionManager;
    public InfoCommand(ConsoleManager console, CollectionManager collectionManager){
        this.console = console;
        this.collectionManager = collectionManager;
    }


    @Override
    public int execute(String[] args) {
        if (args.length != 0){
            console.printErr("Команда не принимает аргументы");
            return 1;
        }
        console.println("Дата инициализации коллекции " + CollectionManager.initTime);
        var collection = collectionManager.getCollection();
        console.println("Тип коллекции: " + collection.getClass().getName());
        console.println("Размер коллекции " + collection.size());
        return 0;
    }

    @Override
    public String toString() {
        return ": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
