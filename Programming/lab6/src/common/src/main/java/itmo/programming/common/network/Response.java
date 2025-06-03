package itmo.programming.common.network;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, представляющий ответ сервера клиенту.
 */
public class Response implements Serializable {
    private final String message;
    private final ResponseStatus status;
    private final Object data;
    private final long timestamp;
    private final String requestId;
    private final String errorDetails;

    /**
     * Создает новый ответ без деталей ошибки.
     *
     * @param message сообщение ответа
     * @param status статус ответа
     * @param data данные ответа
     * @param requestId идентификатор запроса
     */
    public Response(String message, ResponseStatus status, Object data, String requestId) {
        this(message, status, data, requestId, null);
    }

    /**
     * Создает новый ответ с деталями ошибки.
     *
     * @param message сообщение ответа
     * @param status статус ответа
     * @param data данные ответа
     * @param requestId идентификатор запроса
     * @param errorDetails детали ошибки
     */
    public Response(String message, ResponseStatus status,
                    Object data, String requestId, String errorDetails) {
        if (status == null) {
            throw new IllegalArgumentException("Response status не может быть null");
        }
        this.message = message != null ? message : "";
        this.status = status;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
        this.requestId = requestId != null ? requestId : "неизвестный";
        this.errorDetails = errorDetails;
    }

    /**
     * Создает успешный ответ.
     *
     * @param message сообщение ответа
     * @param data данные ответа
     * @param requestId идентификатор запроса
     * @return объект ответа
     */
    public static Response ok(String message, Object data, String requestId) {
        return new Response(message, ResponseStatus.OK, data, requestId);
    }

    /**
     * Создает ответ с ошибкой.
     *
     * @param message сообщение ошибки
     * @param errorDetails детали ошибки
     * @param requestId идентификатор запроса
     * @return объект ответа
     */
    public static Response error(String message, String errorDetails, String requestId) {
        return new Response(message, ResponseStatus.ERROR, null, requestId, errorDetails);
    }

    /**
     * Возвращает сообщение ответа.
     *
     * @return сообщение
     */
    public String getMessage() {
        return message;
    }

    /**
     * Возвращает статус ответа.
     *
     * @return статус
     */
    public ResponseStatus getStatus() {
        return status;
    }

    /**
     * Возвращает данные ответа.
     *
     * @return данные
     */
    public Object getData() {
        return data;
    }

    /**
     * Возвращает временную метку ответа.
     *
     * @return временная метка
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Возвращает идентификатор запроса.
     *
     * @return идентификатор запроса
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Возвращает детали ошибки.
     *
     * @return детали ошибки
     */
    public String getErrorDetails() {
        return errorDetails;
    }

    /**
     * Проверяет, является ли ответ успешным.
     *
     * @return true если ответ успешный, иначе false
     */
    public boolean isSuccess() {
        return status == ResponseStatus.OK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Response response = (Response) o;
        return timestamp == response.timestamp
                && Objects.equals(message, response.message)
                && status == response.status
                && Objects.equals(data, response.data)
                && Objects.equals(requestId, response.requestId)
                && Objects.equals(errorDetails, response.errorDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, status,
                data, timestamp, requestId, errorDetails);
    }

    @Override
    public String toString() {
        return "Response{"
                + "status=" + status
                + ", message='" + message + '\''
                + ", data=" + data
                + ", timestamp=" + timestamp
                + ", requestId='" + requestId + '\''
                + (errorDetails != null ? ", errorDetails='" + errorDetails + '\'' : "")
                + '}';
    }
}
