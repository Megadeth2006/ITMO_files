package itmo.programming.utility;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс адаптер для правильного представления даты LocalDateTime в Json.
 */
public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>,
        JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Сериализация даты для представления в Json из LocalDateTime.
     *
     * @param src объект, который должен быть конвертирован в json.
     *
     * @param typeOfSrc актуальный тип конвертированных данных.
     *
     * @param context context.
     */
    @Override
    public JsonElement serialize(LocalDateTime src,
                                 Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(formatter));
    }

    /**
     * Десериализация даты из Json в LocalDateTime.
     *
     * @param json объект, конвертируемый из Json.
     *
     * @param typeOfT Тип объекта, который мы будем конвертировать.
     *
     * @param context context.
     */
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT,
                                     JsonDeserializationContext context) {
        return LocalDateTime.parse(json.getAsString(),
                formatter);
    }
}
