package itmo.programming.server.commands;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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
    Response execute(Request request) throws NoSuchAlgorithmException, SQLException;

    /**
     * Возвращает описание команды.
     *
     * @return описание команды
     */
    String getDescription();
}
