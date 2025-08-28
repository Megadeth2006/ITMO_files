package itmo.programming.common.network;

import java.io.Serializable;

/**
 * Перечисление статусов ответа сервера.
 */
public enum ResponseStatus implements Serializable {
    /**
     * Операция выполнена успешно.
     */
    OK("Операция выполнена успешно"),

    /**
     * Произошла ошибка при выполнении операции.
     */
    ERROR("Произошла ошибка при выполнении операции"),

    /**
     * Ошибка валидации данных.
     */
    VALIDATION_ERROR("Ошибка валидации данных"),

    /**
     * Запрашиваемый ресурс не найден.
     */
    NOT_FOUND("Запрашиваемый ресурс не найден"),

    /**
     * Неавторизованный доступ.
     */
    UNAUTHORIZED("Неавторизованный доступ"),

    /**
     * Внутренняя ошибка сервера.
     */
    SERVER_ERROR("Внутренняя ошибка сервера"),

    /**
     * Превышено время ожидания операции.
     */
    TIMEOUT("Превышено время ожидания операции"),

    /**
     * Некорректный запрос.
     */
    INVALID_REQUEST("Некорректный запрос");

    private final String description;

    /**
     * Конструктор статуса ответа.
     *
     * @param description описание статуса
     */
    ResponseStatus(String description) {
        this.description = description;
    }

    /**
     * Получить описание статуса.
     *
     * @return описание статуса
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name() + ": " + description;
    }
}
