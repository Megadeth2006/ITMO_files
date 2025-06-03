package itmo.programming.server.commands;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.manager.CollectionManager;

/**
 * Команда удаления элемента по его id.
 */
public class RemoveByIdCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Создает новую команду удаления по id.
     *
     * @param collectionManager менеджер коллекции
     */
    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду удаления элемента по id.
     *
     * @param request запрос на выполнение команды
     * @return ответ с результатом выполнения команды
     */
    @Override
    public Response execute(Request request) {
        try {
            if (request.getArguments().length != 1) {
                return Response.error(
                    "Использование: remove_by_id <id>",
                    "InvalidArgumentCount",
                    request.getClientId()
                );
            }

            final Integer id = Integer.parseInt(request.getArguments()[0]);
            if (!collectionManager.existsById(id)) {
                return Response.error(
                    "Элемент с id " + id + " не найден",
                    "ElementNotFound",
                    request.getClientId()
                );
            }

            if (collectionManager.removeById(id)) {
                return Response.ok(
                    "Элемент успешно удален",
                    null,
                    request.getClientId()
                );
            } else {
                return Response.error(
                    "Не удалось удалить элемент",
                    "RemoveError",
                    request.getClientId()
                );
            }
        } catch (NumberFormatException e) {
            return Response.error(
                "id должен быть числом",
                "InvalidIdFormat",
                request.getClientId()
            );
        }
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
