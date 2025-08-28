package itmo.programming.server.commands;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.common.user.User;
import itmo.programming.server.database.CollectionDaO;
import itmo.programming.server.manager.CollectionManager;
import java.sql.SQLException;

/**
 * Команда очистки коллекции.
 */
public class ClearCommand implements Command {
    private final CollectionManager collectionManager;
    private final CollectionDaO collectionDaO;

    /**
     * Создает новую команду очистки.
     *
     * @param collectionManager менеджер коллекции
     */
    public ClearCommand(CollectionManager collectionManager, CollectionDaO collectionDaO) {
        this.collectionManager = collectionManager;
        this.collectionDaO = collectionDaO;
    }

    /**
     * Выполняет команду очистки коллекции.
     *
     * @param request запрос на выполнение команды
     * @return ответ с результатом выполнения команды
     */
    @Override
    public Response execute(Request request) throws SQLException {
        final User user = request.getUser();
        final String owner = user.getUsername();
        final boolean result = collectionDaO.deleteAll(owner);
        if (result) {
            collectionManager.clear(user.getUsername());
            return Response.ok(
                    "Элементы пользователя " + owner + " удалены",
                    null, null,
                    request.getClientId()
            );
        } else {
            return Response.error("Не получилось очистить", null, null, request.getClientId());
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
} 
