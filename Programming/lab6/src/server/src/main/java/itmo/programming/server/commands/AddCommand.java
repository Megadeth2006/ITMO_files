package itmo.programming.server.commands;

import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.manager.CollectionManager;

/**
 * Команда добавления нового элемента в коллекцию.
 */
public class AddCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Создает новую команду добавления.
     *
     * @param collectionManager менеджер коллекции
     */
    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду добавления элемента.
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

        if (collectionManager.add(spaceMarine)) {
            return Response.ok(
                "Элемент успешно добавлен",
                null,
                request.getClientId()
            );
        } else {
            return Response.error(
                "Не удалось добавить элемент",
                "AddError",
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
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
