package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

/**
 * Команда sum_of_health.
 * Описание команды: вывести сумму значений поля health для всех элементов коллекции.
 */
public class SumOfHealthCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     *
     * @param collection объект менеджера коллекции.
     */
    public SumOfHealthCommand(ConsoleManager console, CollectionManager collection) {
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
        if (args.length != 0) {
            console.printErr("Команда не принимает аргументы");
            return 1;
        }
        final long sumHealth = collection.sumOfHealth();
        console.println("Сумма значений поля health"
                + " для всех элементов коллекции равна: " + sumHealth);
        return 0;
    }

    @Override
    public String toString() {
        return " : вывести"
                + " сумму значений поля health для всех элементов коллекции";
    }
}
