package itmo.programming.server.commands;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;

/**
 * Интерфейс для команд.
 */
public interface Command {
    /**
     * Выполняет команду.
     *
     * @param request запрос от клиента
     * @return ответ клиенту
     */
    Response execute(Request request);

    /**
     * Возвращает описание команды.
     *
     * @return описание команды
     */
    String getDescription();
}
