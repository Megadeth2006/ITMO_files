package itmo.programming.command;

import itmo.programming.manager.ConsoleManager;

/**
 * Команда exit.
 * Описание команды: завершить программу (без сохранения в файл).
 */
public class ExitCommand implements CommandInterface {
    ConsoleManager console;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     */
    public ExitCommand(ConsoleManager console) {
        this.console = console;
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
        console.println("Завершение программы");
        System.exit(0);
        return 0;
    }

    @Override
    public String toString() {
        return " : завершить программу (без сохранения в файл)";
    }
}
