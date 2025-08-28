package itmo.programming.server.commands;

import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.database.CollectionDaO;
import itmo.programming.server.manager.CollectionManager;
import java.sql.SQLException;

/**
 * Команда обновления элемента коллекции по id.
 */
public class UpdateCommand implements Command {
    private final CollectionManager collectionManager;
    private final CollectionDaO collectionDaO;

    /**
     * Конструктор команды обновления.
     *
     * @param collectionManager менеджер коллекции
     */
    public UpdateCommand(CollectionManager collectionManager, CollectionDaO collectionDaO) {
        this.collectionManager = collectionManager;
        this.collectionDaO = collectionDaO;
    }

    @Override
    public Response execute(Request request) {
        if (request.getData().equals("check") && request.getArguments().length == 1) {
            final Integer id = Integer.parseInt(request.getArguments()[0]);
            if (collectionManager.existsById(id)) {
                final String owner = request.getUser().getUsername();
                if (!collectionManager.checkOwner(id, owner)) {
                    return Response.error("anotherOwner", null, null, request.getClientId());
                }
                return Response.ok("exist", null, null, request.getClientId());
            } else {
                return Response.error("notExist", null, null, request.getClientId());
            }
        }

        if (request.getArguments().length != 1) {
            return Response.error(
                "Использование: update <id>",
                "InvalidArgumentCount", null,
                request.getClientId()
            );
        }

        try {
            final int id = Integer.parseInt(request.getArguments()[0]);
            final SpaceMarine spaceMarine = (SpaceMarine) request.getData();

            if (spaceMarine == null) {
                return Response.error(
                    "Данные для обновления отсутствуют",
                    "NullSpaceMarine", null,
                    request.getClientId()
                );
            } else {
                final String owner = request.getUser().getUsername();
                final boolean updated = collectionDaO.update(id, spaceMarine, owner);
                if (!updated) {
                    return Response.error("Не удалось обновить элемент в базе данных",
                            null, null, request.getClientId());
                }
                final boolean exists = collectionManager.updateById(id,
                        spaceMarine, request.getUser().getUsername());
                if (exists) {
                    return Response.ok(
                        "Элемент успешно обновлен",
                        null, null,
                        request.getClientId()
                    );
                } else {
                    return Response.error(
                        "Элемент с id " + id + " не найден",
                        "ElementNotFound", null,
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
