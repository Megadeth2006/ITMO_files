package itmo.programming.manager;

import java.util.Scanner;

/**
 * Класс для взаимодействия с каналом вывода.
 */
public class ConsoleManager {
    private static Scanner consoleScanner = new Scanner(System.in);
    private static Scanner fileScanner = null;

    /**
     * Прочитать и вернуть данные с консоли или файла.
     */
    public String readln() {
        return (fileScanner == null ? consoleScanner : fileScanner).nextLine();
    }

    /**
     * Вывести параметр в консоль без переноса строки.
     *
     * @param object объект.
     */
    public void print(Object object) {
        System.out.print(object);
    }

    /**
     * Вывести параметр в консоль с переносом строки.
     *
     * @param object объект.
     */
    public void println(Object object) {
        System.out.print(object + "\n");
    }

    /**
     * Вывести ошибку.
     *
     * @param message сообщение.
     */
    public void printErr(String message) {
        System.out.println("Возникла ошибка: " + message);
    }

    /**
     * Вывести предупреждение.
     *
     * @param message сообщение.
     */
    public void printWarning(String message) {
        System.out.println("Предупреждение: " + message);
    }
}
