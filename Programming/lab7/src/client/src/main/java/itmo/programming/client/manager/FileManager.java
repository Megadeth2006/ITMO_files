package itmo.programming.client.manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

/**
 * Менеджер для работы с файлами.
 */
public class FileManager {


    /**
     * Метод принимает имя или путь к файлу и выдает File.
     *
     * @param path путь или имя.
     */
    public File getFile(String path) throws InvalidPathException {

        final Path filePath = Path.of(path);

        if (Files.exists(filePath) && Files.isRegularFile(filePath)) {
            return filePath.toFile();
        }
        final String envPath = System.getenv("LAB_PATH");
        if (envPath != null) {
            final Path envFilePath = Path.of(envPath, path);
            if (Files.exists(envFilePath) && Files.isRegularFile(envFilePath)) {
                return envFilePath.toFile();
            }
        }
        return null; // файл не найден
    }

    /**
     * Проверяет, можно ли безопасно прочитать файл.
     *
     * @param file объект файла
     * @return true если файл доступен для чтения, иначе false
     */
    public boolean canReadFile(File file) {
        return file != null && file.exists() && file.isFile() && file.canRead() && !file.isHidden();
    }



    /**
     * Читает команды из файла в виде массива строк.
     *
     * @param path путь к файлу
     * @return массив строк с командами
     */
    public String[] readCommandsFromFile(String path) {
        File file = null;
        try {
            file = getFile(path);
        } catch (InvalidPathException e) {
            System.out.println("Неверный путь к файлу!");
            return null;
        }

        if (!canReadFile(file)) {
            System.err.println("Ошибка: файл не найден или не может быть прочитан.");
            return null;
        }

        try {
            final List<String> lines = Files.readAllLines(file.toPath());
            if (lines.isEmpty()) {
                System.err.println("Ошибка: файл пустой.");
                return new String[0];
            }
            return lines.stream().map(String::trim).filter(
                    line -> !line.isEmpty()).toArray(String[]::new);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            return null;
        }
    }
}
