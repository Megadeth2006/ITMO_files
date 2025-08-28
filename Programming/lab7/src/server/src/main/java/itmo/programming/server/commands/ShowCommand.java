package itmo.programming.server.commands;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.manager.CollectionManager;

/**
 * Команда вывода всех элементов коллекции.
 */
public class ShowCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Создает новую команду вывода элементов.
     *
     * @param collectionManager менеджер коллекции
     */
    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду вывода всех элементов коллекции.
     *
     * @param request запрос на выполнение команды
     * @return ответ со всеми элементами коллекции
     */
    @Override
    public Response execute(Request request) {
        final String elements = collectionManager.getAllString();
        if (elements.isEmpty()) {
            return Response.ok(
                "Коллекция пуста",
                null, null,
                request.getClientId()
            );
        }

        return Response.ok(
            "Элементы коллекции:",
            elements, null,
            request.getClientId()
        );
    }

    @Override
    public String getDescription() {
        return "show : вывести все элементы коллекции";
    }
}
