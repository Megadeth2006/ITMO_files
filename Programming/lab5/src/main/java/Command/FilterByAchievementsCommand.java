package Command;

import Manager.CollectionManager;
import Manager.ConsoleManager;
import Model.SpaceMarine;

import java.util.List;

public class FilterByAchievementsCommand implements CommandInterface{
    ConsoleManager console;
    CollectionManager collection;
    public FilterByAchievementsCommand(ConsoleManager console, CollectionManager collection){
        this.console = console;
        this.collection = collection;
    }
    @Override
    public int execute(String[] args) {
        String argument = "";
        if (args.length != 0){
            argument = args[0];
        }
        List<SpaceMarine> filterList = collection.filterByAchievements(argument);
        for (SpaceMarine object: filterList){
            console.print(object);
        }
        if (!filterList.isEmpty()){
            console.print("Выведены элементы, значение поля achievements которых равно заданному");
        }else{
            console.print("Нет элементов, значение поля achievements которых равно заданному");
        }

        return 0;
    }

    @Override
    public String toString() {
        return " : вывести элементы, значение поля achievements которых равно заданному";
    }
}
