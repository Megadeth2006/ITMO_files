package itmo.programming.server.commands;

import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.database.CollectionDaO;
import itmo.programming.server.manager.CollectionManager;
import java.sql.SQLException;

/**
 * Команда добавления нового элемента в коллекцию.
 */
public class AddCommand implements Command {
    private final CollectionManager collectionManager;
    private final CollectionDaO collectiondao;


    /**
     * Создает новую команду добавления.
     *
     * @param collectionManager менеджер коллекции
     */
    public AddCommand(CollectionManager collectionManager, CollectionDaO collectiondao) {
        this.collectionManager = collectionManager;
        this.collectiondao = collectiondao;
    }

    /**
     * Выполняет команду добавления элемента.
     *
     * @param request запрос, содержащий данные для добавления
     * @return ответ с результатом выполнения команды
     */
    @Override
    public Response execute(Request request) {
        try {
            final SpaceMarine spaceMarine = (SpaceMarine) request.getData();
            final String username = request.getUser().getUsername();
            final int generatedId = collectiondao.insert(spaceMarine, username);
            spaceMarine.setId(generatedId);
            spaceMarine.setOwner(username);

            synchronized (collectionManager) { // Синхронизованно добавляем в коллекцию
                collectionManager.add(spaceMarine, username);
            }
            return Response.ok("Элемент успешно добавлен (id = " + generatedId + ").",
                    null, null, request.getClientId());
        } catch (SQLException e) {
            return Response.error("Ошибка при добавлении элемента",
                    null, null, request.getClientId());
        } catch (NullPointerException e) {
            return Response.error("Невозможно добавить пустой элемент!",
                    null, null, request.getClientId());
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
