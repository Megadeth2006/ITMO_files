package itmo.programming.server.commands;

import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.manager.CollectionManager;

/**
 * Команда добавления нового элемента в коллекцию, если он меньше минимального.
 */
public class AddIfMinCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Создает новую команду добавления минимального элемента.
     *
     * @param collectionManager менеджер коллекции
     */
    public AddIfMinCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду добавления элемента, если он меньше минимального.
     *
     * @param request запрос, содержащий данные для добавления
     * @return ответ с результатом выполнения команды
     */
    @Override
    public Response execute(Request request) {
        final SpaceMarine spaceMarine = (SpaceMarine) request.getData();
        
        if (spaceMarine == null) {
            return Response.error(
                "Данные для добавления отсутствуют",
                "NullSpaceMarine",
                request.getClientId()
            );
        }

        if (collectionManager.addIfMin(spaceMarine)) {
            return Response.ok(
                "Элемент успешно добавлен",
                null,
                request.getClientId()
            );
        } else {
            return Response.error(
                "Элемент не является минимальным, добавление отменено",
                "NotMinElement",
                request.getClientId()
            );
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "add_if_min {element} : "
               + "добавить новый элемент в коллекцию, если его значение меньше минимального";
    }
}
