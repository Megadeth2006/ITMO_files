package itmo.programming.server.commands;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.manager.CollectionManager;

/**
 * Команда вывода информации о коллекции.
 */
public class InfoCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Создает новую команду информации.
     *
     * @param collectionManager менеджер коллекции
     */
    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду вывода информации о коллекции.
     *
     * @param request запрос на выполнение команды
     * @return ответ с информацией о коллекции
     */
    @Override
    public Response execute(Request request) {
        final String info = collectionManager.getInfo();
        return Response.ok(
            "Информация о коллекции",
            info, null,
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
        return "info : вывести информацию о коллекции";
    }
} 
