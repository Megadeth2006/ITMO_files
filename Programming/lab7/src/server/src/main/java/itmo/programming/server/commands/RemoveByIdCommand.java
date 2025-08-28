package itmo.programming.server.commands;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.database.CollectionDaO;
import itmo.programming.server.manager.CollectionManager;
import java.sql.SQLException;

/**
 * Команда удаления элемента по его id.
 */
public class RemoveByIdCommand implements Command {
    private final CollectionManager collectionManager;
    private final CollectionDaO collectionDaO;

    /**
     * Создает новую команду удаления по id.
     *
     * @param collectionManager менеджер коллекции
     */
    public RemoveByIdCommand(CollectionManager collectionManager, CollectionDaO collectionDaO) {
        this.collectionManager = collectionManager;
        this.collectionDaO = collectionDaO;
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
                    "InvalidArgumentCount", null,
                    request.getClientId()
                );
            }

            final Integer id = Integer.parseInt(request.getArguments()[0]);
            if (!collectionManager.existsById(id)) {
                return Response.error(
                    "Элемент с id " + id + " не найден",
                    "ElementNotFound", null,
                    request.getClientId()
                );
            }
            final String owner = request.getUser().getUsername();
            if (!collectionManager.checkOwner(id, owner)) {
                return Response.error("Элемент с id " + id
                                + " принадлежит другому владельцу",
                        "AnotherOwner", null, request.getClientId());
            }

            if (collectionDaO.deleteById(id, owner)) {
                if (collectionManager.removeById(id, owner)) {
                    return Response.ok(
                            "Элемент успешно удален",
                            null, null,
                            request.getClientId()
                    );
                } else {
                    return Response.error(
                            "Не удалось удалить элемент",
                            "RemoveError", null,
                            request.getClientId()
                    );
                }
            }

        } catch (NumberFormatException e) {
            return Response.error(
                "id должен быть числом",
                "InvalidIdFormat", null,
                request.getClientId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
