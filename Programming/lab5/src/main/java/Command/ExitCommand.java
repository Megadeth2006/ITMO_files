package Command;

import Manager.ConsoleManager;


public class ExitCommand implements CommandInterface {
    ConsoleManager console;
    public ExitCommand(ConsoleManager console){
        this.console = console;
    }

    @Override
    public int execute(String[] args) {
        if (args.length != 0){
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
