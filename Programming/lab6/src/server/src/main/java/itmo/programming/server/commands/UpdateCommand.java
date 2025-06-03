package itmo.programming.server.commands;

import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.manager.CollectionManager;

/**
 * Команда обновления элемента коллекции по id.
 */
public class UpdateCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Конструктор команды обновления.
     *
     * @param collectionManager менеджер коллекции
     */
    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        if (request.getData().equals("check") && request.getArguments().length == 1) {
            if (collectionManager.existsById(Integer.parseInt(request.getArguments()[0]))) {
                return Response.ok("exist", null, request.getClientId());
            } else {
                return Response.error("not", null, request.getClientId());
            }
        }

        if (request.getArguments().length != 1) {
            return Response.error(
                "Использование: update <id>",
                "InvalidArgumentCount",
                request.getClientId()
            );
        }

        try {
            final int id = Integer.parseInt(request.getArguments()[0]);
            final SpaceMarine spaceMarine = (SpaceMarine) request.getData();

            if (spaceMarine == null) {
                return Response.error(
                    "Данные для обновления отсутствуют",
                    "NullSpaceMarine",
                    request.getClientId()
                );
            } else {
                final boolean exists = collectionManager.updateById(id, spaceMarine);
                if (exists) {
                    return Response.ok(
                        "Элемент успешно обновлен",
                        null,
                        request.getClientId()
                    );
                } else {
                    return Response.error(
                        "Элемент с id " + id + " не найден",
                        "ElementNotFound",
                        request.getClientId()
                    );
                }
            }
        } catch (NumberFormatException e) {
            return Response.error(
                "id должен быть числом",
                "InvalidIdFormat",
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
        return "update id {element} : обновить значение элемента коллекции по id";
    }
}
