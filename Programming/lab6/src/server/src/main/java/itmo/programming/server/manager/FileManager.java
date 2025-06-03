package itmo.programming.server.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.utilities.Adapter;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.TreeSet;

/**
 * Класс, отвечающий за работу с файлом на сервере.
 */
public class FileManager {
    private static TreeSet<SpaceMarine> collection;
    private final Gson gson;
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса.
     *
     * @param collectionManager менеджер коллекции.
     */
    public FileManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new Adapter())
                .setPrettyPrinting()
                .create();
    }

    /**
     * Поиск файла в системе.
     *
     * @return абсолютный путь к файлу
     */
    public String findFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            return file.getAbsolutePath();
        } else {
            final String envPath = System.getenv("LAB_PATH");
            file = new File(envPath, fileName);
            if (file.isFile() && file.exists()) {
                return file.getAbsolutePath();
            }
        }
        return fileName;
    }



    /**
     * Загружает коллекцию из файла.
     *
     * @param fileName имя файла
     * @throws IOException при ошибке чтения файла
     */
    public void loadFromFile(String fileName) throws FileNotFoundException, IOException {

        final String filePath = findFile(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            final StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            final TreeSet<SpaceMarine> loadedCollection = gson.fromJson(
                    jsonString.toString(),
                    new TypeToken<TreeSet<SpaceMarine>>() {}.getType()
            );

            if (loadedCollection != null) {
                collection = loadedCollection;
                collectionManager.setCollection(collection);
                collectionManager.updateNextId();

            }
        } catch (JsonParseException e) {
            throw new IOException("Ошибка при парсинге JSON: " + e.getMessage());
        }
    }

    /**
     * Сохраняет коллекцию в файл.
     *
     * @param fileName имя файла
     * @throws IOException при ошибке записи в файл
     */
    public void saveToFile(String fileName) throws FileNotFoundException, IOException {
        final String filePath = findFile(fileName);
        collection = collectionManager.getCollection();
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filePath))) {
            final String json = gson.toJson(collection);
            out.write(json.getBytes());
        }
    }
}
