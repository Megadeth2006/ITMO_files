package itmo.programming.server.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Менеджер для работы со скриптами.
 * Обеспечивает выполнение скриптов и защиту от рекурсии.
 * Использует стеки для отслеживания путей к файлам и сканеров.
 */
public class ScriptManager {
    private static final Stack<String> pathStack = new Stack<>();
    private static final Stack<Scanner> scanners = new Stack<>();

    /**
     * Добавить путь к файлу в стек (реализация проверки на рекурсивность).
     *
     * @param path относительный путь.
     */
    public static void addToStack(String path) throws FileNotFoundException {
        pathStack.push(new File(path).getAbsolutePath());
        scanners.push(new Scanner(new File(path)));
    }

    /**
     * Проверка на рекурсивность скрипта.
     *
     * @param path относительный путь к файлу.
     */
    public static boolean isRecursive(String path) {
        return pathStack.contains(new File(path).getAbsolutePath());
    }

    /**
     * Удалить путь к файлу из стека.
     */
    public static void removeFromStack() {
        scanners.pop();
        pathStack.pop();
    }

    /**
     * Получить объект последнего использованного канала входа.
     */
    public static Scanner getLastScanner() {
        return scanners.lastElement();
    }

}
