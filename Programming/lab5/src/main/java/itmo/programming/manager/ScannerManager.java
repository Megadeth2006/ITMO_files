package itmo.programming.manager;

import java.util.Scanner;

public class ScannerManager {
    private static Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner1) {
        scanner = scanner1;
    }

    private ScannerManager() {
    }
}
