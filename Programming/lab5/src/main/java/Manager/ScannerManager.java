package Manager;

import java.util.Scanner;

public class ScannerManager {
    private static final Scanner scanner = new Scanner(System.in);
    public static Scanner getScanner(){
        return scanner;
    }

    private ScannerManager(){}
}
