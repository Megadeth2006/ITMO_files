package itmo.programming.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import itmo.programming.model.SpaceMarine;
import itmo.programming.utility.LocalDateTimeAdapter;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Класс для работы с файлом.
 */
public class FileManager {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();
    private String fileName;
    private final ConsoleManager console;
    private final CollectionManager collection;

    /**
     * Конструктор класса.
     *
     * @param fileName название файла.
     *
     * @param console объект менеджера консоли.
     *
     * @param collection объект менеджера коллекции.
     */
    public FileManager(String fileName, ConsoleManager console, CollectionManager collection) {
        this.fileName = fileName;
        this.console = console;
        this.collection = collection;
    }

    /**
     * Метод для проверки возможности прочитать файл для исполнения.
     *
     * @param file объект файла.
     *
     * @param console объект менеджера консоли.
     */
    public boolean canReadFile(File file, ConsoleManager console) {
        if (!file.exists()) {
            console.printErr("Файл не найден");
            return false;
        }
        if (!file.canRead()) {
            console.printErr("Файл не может быть прочитан");
            return false;
        }
        if (file.isHidden()) {
            console.printErr("Файл скрыт");
            return false;
        }
        if (!file.isFile()) {
            console.printErr("Это не файл");
            return false;
        }
        return true;
    }

    /**
     * Метод для записи коллекции в файл Json.
     */
    public void writeCollection() throws FileNotFoundException {

        final TreeSet<SpaceMarine> currentCollection = collection.getCollection();
        final File file = new File(fileName);
        if (file.exists()) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                         fileOutputStream)
            ) {
                bufferedOutputStream.write(gson.toJson(currentCollection).getBytes());

                console.println("Коллекция успешно записана в файл " + fileName);
            } catch (IOException e) {
                console.printErr("Файл не может быть открыт");
            }
        } else {
            console.printErr("Файла по пути " + fileName + " не существует");
        }
    }

    /**
     * Метод для чтения коллекции из файла Json.
     *
     * @param collection объект менеджера коллекции.
     */
    public void readCollection(CollectionManager collection) {

        if (fileName != null && !fileName.isEmpty()) {
            try (InputStreamReader fileReader = new InputStreamReader(
                    new FileInputStream(fileName))) {

                final var reader = new BufferedReader(fileReader);
                var jsonString = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        jsonString.append(line);
                    }
                }
                if (jsonString.isEmpty()) {
                    jsonString = new StringBuilder("[]");
                }
                if (jsonString.toString().equals("[]")) {
                    console.printWarning("Коллекция в файле пуста, но ничего страшного");
                }
                final Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class,
                                (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) ->
                                LocalDateTime.parse(json.getAsString()))
                        .create();
                final Type collectionType = new TypeToken<PriorityQueue<SpaceMarine>>() {
                }.getType();
                final PriorityQueue<SpaceMarine> currentCollection = gson.fromJson(
                        jsonString.toString(), collectionType);

                for (SpaceMarine spaceMarine : currentCollection) {
                    if (ValidationManager.isValidSpaceMarine(
                            spaceMarine, collection) && ValidationManager.isValidCoordinates(
                            spaceMarine) && ValidationManager.isValidChapter(spaceMarine)
                            && ValidationManager.isValidEnum(spaceMarine)) {
                        collection.add(spaceMarine);
                    } else {
                        console.printErr("В файле содержится"
                                + " коллекция с данными в неверном формате");
                    }
                }

                console.println("Коллекция по адресу: " + fileName + " загружена");

            } catch (FileNotFoundException e) {
                console.printErr("Файла не существует");
            } catch (NoSuchElementException e) {
                console.printErr("Файл пустой");
            } catch (JsonParseException e) {
                console.printErr("В файле нет коллекции нужного вида");
            } catch (IllegalStateException | IOException e) {
                console.printErr("Непредвиденная ошибка");
            }

        } else {
            console.printErr("Файл не найден");
        }
        ;
    }

}
