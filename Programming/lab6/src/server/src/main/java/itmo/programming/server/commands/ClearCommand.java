package itmo.programming.server.commands;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.manager.CollectionManager;

/**
 * Команда очистки коллекции.
 */
public class ClearCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Создает новую команду очистки.
     *
     * @param collectionManager менеджер коллекции
     */
    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду очистки коллекции.
     *
     * @param request запрос на выполнение команды
     * @return ответ с результатом выполнения команды
     */
    @Override
    public Response execute(Request request) {
        collectionManager.clear();
        return Response.ok(
            "Коллекция очищена",
            null,
            request.getClientId()
        );
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
