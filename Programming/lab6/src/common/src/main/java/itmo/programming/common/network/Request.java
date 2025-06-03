package itmo.programming.common.network;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Класс, представляющий запрос клиента к серверу.
 */
public class Request implements Serializable {
    private final String commandName;
    private final String[] arguments;
    private final Object data;
    private final long timestamp;
    private final String clientId;

    /**
     * Создает новый запрос.
     *
     * @param commandName название команды
     * @param arguments аргументы команды
     * @param data дополнительные данные
     * @param clientId идентификатор клиента
     */
    public Request(String commandName, String[] arguments, Object data, String clientId) {
        if (commandName == null || commandName.trim().isEmpty()) {
            throw new IllegalArgumentException("Название команды не может быть"
                    + " пустой строкой или null");
        }
        this.commandName = commandName;
        this.arguments = arguments != null ? Arrays.copyOf(arguments,
                arguments.length) : new String[0];
        this.data = data;
        this.timestamp = System.currentTimeMillis();
        this.clientId = clientId != null ? clientId : "неизвестный";
    }

    /**
     * Возвращает название команды.
     *
     * @return название команды
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Возвращает аргументы команды.
     *
     * @return массив аргументов
     */
    public String[] getArguments() {
        return Arrays.copyOf(arguments, arguments.length);
    }

    /**
     * Возвращает дополнительные данные запроса.
     *
     * @return объект с данными
     */
    public Object getData() {
        return data;
    }

    /**
     * Возвращает идентификатор клиента.
     *
     * @return идентификатор клиента
     */
    public String getClientId() {
        return clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Request request = (Request) o;
        return timestamp == request.timestamp
                && Objects.equals(commandName, request.commandName)
                && Arrays.equals(arguments, request.arguments)
                && Objects.equals(data, request.data)
                && Objects.equals(clientId, request.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandName,
                data, timestamp, clientId, Arrays.hashCode(arguments));
    }

    @Override
    public String toString() {
        return "Request{"
                + "commandName='" + commandName + '\''
                + ", arguments=" + Arrays.toString(arguments)
                + ", data=" + data
                + ", timestamp=" + timestamp
                + ", clientId='" + clientId + '\''
                + '}';
    }
}
