package itmo.programming.server.commands;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.manager.CollectionManager;

/**
 * Команда print_ascending.
 */
public class PrintAscendingCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Создает новую команду вывода элементов по возрастанию.
     *
     * @param collectionManager менеджер коллекции
     */
    public PrintAscendingCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду вывода элементов в порядке возрастания.
     *
     * @param request запрос на выполнение команды
     * @return ответ с отсортированными элементами
     */
    @Override
    public Response execute(Request request) {
        final var sorted = collectionManager.getAscending();
        if (sorted.isEmpty()) {
            return Response.ok(
                "Коллекция пуста",
                sorted, null,
                request.getClientId()
            );
        }
        return Response.ok(
            "Элементы коллекции в порядке возрастания:",
            sorted, null,
            request.getClientId()
        );
    }

    @Override
    public String getDescription() {
        return "print_ascending : вывести элементы коллекции в порядке возрастания";
    }
}
