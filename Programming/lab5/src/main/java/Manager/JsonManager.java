package Manager;
import Model.SpaceMarine;
import Utility.LocalDateAdapter;
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
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    private final String fileName;
    private final ConsoleManager console;


    public JsonManager(String fileName, ConsoleManager console) {
        this.fileName = fileName;
        this.console = console;
    }

    public void writeCollection(TreeSet<SpaceMarine> collection) throws FileNotFoundException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName); BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);){
            bufferedOutputStream.write(gson.toJson(collection).getBytes(StandardCharsets.UTF_8));
            console.println("Коллекция успешно записана в файл " + fileName);
        }catch (IOException e){
            console.printErr("Файл не может быть открыт");
        }
    }
    public void readCollection(CollectionManager collectionManager){

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
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) ->
                                LocalDateTime.parse(json.getAsString()))
                        .create();
                Type collectionType = new TypeToken<PriorityQueue<SpaceMarine>>() {}.getType();
                PriorityQueue<SpaceMarine> collection = gson.fromJson(jsonString.toString(), collectionType); //добавить проверку на валидность SpaceMarine

                for (SpaceMarine spaceMarine : collection){
                    if (ValidationManager.isValidSpaceMarine(spaceMarine, collectionManager) && ValidationManager.isValidCoordinates(spaceMarine) && ValidationManager.isValidChapter(spaceMarine) && ValidationManager.isValidEnum(spaceMarine)){
                        collectionManager.add(spaceMarine);
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
