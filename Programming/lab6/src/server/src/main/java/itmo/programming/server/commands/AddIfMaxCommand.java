package itmo.programming.server.commands;

import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.manager.CollectionManager;

/**
 * Команда добавления нового элемента в коллекцию, если он превышает максимальный.
 */
public class AddIfMaxCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Создает новую команду добавления максимального элемента.
     *
     * @param collectionManager менеджер коллекции
     */
    public AddIfMaxCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду добавления элемента, если он больше максимального.
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

        if (collectionManager.addIfMax(spaceMarine)) {
            return Response.ok(
                "Элемент успешно добавлен",
                null,
                request.getClientId()
            );
        } else {
            return Response.error(
                "Элемент не является максимальным, добавление отменено",
                "NotMaxElement",
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
        return "add_if_max {element} : "
                + "добавить новый элемент в коллекцию,"
                + " если его значение превышает значение наибольшего элемента";
    }
}
