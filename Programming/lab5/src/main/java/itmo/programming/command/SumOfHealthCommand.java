package itmo.programming.command;

import itmo.programming.manager.CollectionManager;
import itmo.programming.manager.ConsoleManager;

public class SumOfHealthCommand implements CommandInterface {
    ConsoleManager console;
    CollectionManager collection;

    public SumOfHealthCommand(ConsoleManager console, CollectionManager collection) {
        this.console = console;
        this.collection = collection;
    }

    @Override
    public int execute(String[] args) {
        if (args.length != 0) {
            console.printErr("Команда не принимает аргументы");
            return 1;
        }
        long sumHealth = collection.sumOfHealth();
        console.print("Сумма значений поля health для всех элементов коллекции равна: " + sumHealth);
        return 0;
    }

    @Override
    public String toString() {
        return " : вывести сумму значений поля health для всех элементов коллекции";
    }
}
