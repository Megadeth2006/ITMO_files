package Utility;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class DefaultConsole implements Console{
    private static Scanner consoleScanner = new Scanner(System.in);
    private static Scanner fileScanner = null;
    public String readln() throws NoSuchElementException, IllegalStateException {
        return (fileScanner == null? consoleScanner: fileScanner ).nextLine();
    }


    public void print(Object object) {
        System.out.println(object);
    }

    public void println(Object object) {
        System.out.println(object + "\n");
    }
    public void printErr(String message){
        System.out.println("Возникла ошибка:" + message);

    }
}
