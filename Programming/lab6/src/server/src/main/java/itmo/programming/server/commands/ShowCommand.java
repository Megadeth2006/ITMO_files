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
        final var elements = collectionManager.getAll();
        if (elements.isEmpty()) {
            return Response.ok(
                "Коллекция пуста",
                elements,
                request.getClientId()
            );
        }


        return Response.ok(
            "Элементы коллекции:",
            elements,
            request.getClientId()
        );
    }

    @Override
    public String getDescription() {
        return "show : вывести все элементы коллекции";
    }
}
