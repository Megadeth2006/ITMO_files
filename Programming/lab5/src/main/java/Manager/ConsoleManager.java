package Manager;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleManager {
    private static Scanner consoleScanner = new Scanner(System.in);
    private static Scanner fileScanner = null;

    public String readln() throws NoSuchElementException, IllegalStateException {
        return (fileScanner == null ? consoleScanner : fileScanner).nextLine();
    }


    public void print(Object object) {
        System.out.println(object);
    }

    public void println(Object object) {
        System.out.print(object + "\n");
    }

    public void printErr(String message) {
        System.out.println("Возникла ошибка: " + message);

    }
}
