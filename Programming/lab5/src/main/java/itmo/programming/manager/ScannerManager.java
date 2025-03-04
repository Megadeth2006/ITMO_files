package itmo.programming.manager;

import java.util.Scanner;

/**
 * Класс для работы со сканером. (Входной канал).
 */
public class ScannerManager {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Получить объект сканера.
     */
    public static Scanner getScanner() {
        return scanner;
    }

    /**
     * Определить сканер.
     *
     * @param scanner1 объект сканера.
     */
    public static void setScanner(Scanner scanner1) {
        scanner = scanner1;
    }


}
