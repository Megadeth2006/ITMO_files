package itmo.programming.server.commands;

import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.manager.CollectionManager;
import java.util.List;

/**
 * Команда фильтрации элементов коллекции по полю achievements.
 */
public class FilterByAchievementsCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Создает новую команду фильтрации по достижениям.
     *
     * @param collectionManager менеджер коллекции
     */
    public FilterByAchievementsCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду фильтрации элементов по полю achievements.
     *
     * @param request запрос, содержащий строку для фильтрации
     * @return ответ с результатом выполнения команды
     */
    @Override
    public Response execute(Request request) {
        if (request.getArguments().length != 1) {
            return Response.error(
                "Использование: filter_by_achievements <achievements>",
                "InvalidArgumentCount", null,
                request.getClientId()
            );
        }

        final String achievements = request.getArguments()[0];
        final List<SpaceMarine> filtered = collectionManager.filterByAchievements(achievements);

        if (filtered.isEmpty()) {
            return Response.ok(
                "Элементы с заданным значением поля achievements не найдены",
                filtered, null,
                request.getClientId()
            );
        } else {
            return Response.ok(
                "Найдены элементы с заданным значением поля achievements",
                filtered, null,
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
        return "filter_by_achievements achievements : "
                + "вывести элементы, значение поля achievements которых равно заданному";
    }
}
