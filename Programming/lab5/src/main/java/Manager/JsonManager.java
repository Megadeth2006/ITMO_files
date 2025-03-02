package Manager;
import Model.SpaceMarine;
import Utility.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class JsonManager {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();
    private String fileName;
    private final ConsoleManager console;
    private final CollectionManager collection;



    public JsonManager(String fileName, ConsoleManager console, CollectionManager collection) {
        this.fileName = fileName;
        this.console = console;
        this.collection = collection;
    }

    public void writeCollection() throws FileNotFoundException {

        TreeSet<SpaceMarine> currentCollection = collection.getCollection();
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName); BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)){

            bufferedOutputStream.write(gson.toJson(currentCollection).getBytes());

            console.println("Коллекция успешно записана в файл " + fileName);
        }catch (IOException e){
            console.printErr("Файл не может быть открыт");
        }
    }

    public void readCollection(CollectionManager collection){

        if (fileName != null && !fileName.isEmpty()){
            try (InputStreamReader fileReader =  new InputStreamReader(new FileInputStream(fileName))){

                var reader = new BufferedReader(fileReader);
                var jsonString = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null){
                    line = line.trim();
                    if (!line.isEmpty()){
                        jsonString.append(line);
                    }
                }
                if (jsonString.isEmpty()){
                    jsonString = new StringBuilder("[]");
                }
                if (jsonString.toString().equals("[]")){
                    console.print("Коллекция в файле пуста, но ничего страшного");
                }
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) ->
                                LocalDateTime.parse(json.getAsString()))
                        .create();
                Type collectionType = new TypeToken<PriorityQueue<SpaceMarine>>() {}.getType();
                PriorityQueue<SpaceMarine> currentCollection = gson.fromJson(jsonString.toString(), collectionType);

                for (SpaceMarine spaceMarine : currentCollection){
                    if (ValidationManager.isValidSpaceMarine(spaceMarine, collection) && ValidationManager.isValidCoordinates(spaceMarine) && ValidationManager.isValidChapter(spaceMarine) && ValidationManager.isValidEnum(spaceMarine)){
                        collection.add(spaceMarine);
                    }
                    else{
                        console.printErr("В файле содержится коллекция с данными в неверном формате");
                    }
                }

                console.println("Коллекция по адресу: " + fileName +  " загружена");

            } catch (FileNotFoundException e) {
                console.printErr("Файла не существует");
            }
            catch (NoSuchElementException e){
                console.printErr("Файл пустой");
            }
            catch (JsonParseException e){
                console.printErr("В файле нет коллекции нужного вида");
            }
            catch (IllegalStateException | IOException e){
                console.printErr("Непредвиденная ошибка");
            }

        }else{
            console.printErr("Файл не найден");
        };
    }

}
