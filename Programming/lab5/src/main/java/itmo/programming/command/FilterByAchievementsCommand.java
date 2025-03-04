package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.SpaceMarine;
import java.util.List;

/**
 * Команда filter_by_achievements.
 * Описание команды: вывести элементы, значение поля achievements которых равно заданному.
 */
public class FilterByAchievementsCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     *
     * @param collection объект менеджера коллекции.
     */
    public FilterByAchievementsCommand(ConsoleManager console, CollectionManager collection) {
        this.console = console;
        this.collection = collection;
    }
    /**
     * Исполнение команды.
     *
     * @param args аргументы.
     */

    @Override
    public int execute(String[] args) {
        String argument = "";
        if (args.length != 0) {
            argument = args[0];
        }
        final List<SpaceMarine> filterList = collection.filterByAchievements(argument);
        for (SpaceMarine object : filterList) {
            console.print(object);
        }
        if (!filterList.isEmpty()) {
            console.println("Выведены элементы,"
                    + " значение поля achievements которых равно заданному");
        } else {
            console.println("Нет элементов, значение поля achievements которых равно заданному");
        }

        return 0;
    }

    @Override
    public String toString() {
        return " : вывести элементы, значение поля achievements которых равно заданному";
    }
}
