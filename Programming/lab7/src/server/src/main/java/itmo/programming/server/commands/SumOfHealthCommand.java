package itmo.programming.server.commands;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.server.manager.CollectionManager;

/**
 * Команда подсчета суммы значений поля health.
 */
public class SumOfHealthCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Создает новую команду подсчета суммы health.
     *
     * @param collectionManager менеджер коллекции
     */
    public SumOfHealthCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду подсчета суммы значений поля health.
     *
     * @param request запрос на выполнение команды
     * @return ответ с суммой значений поля health
     */
    @Override
    public Response execute(Request request) {
        final double sum = collectionManager.sumOfHealth();
        return Response.ok(
            "Сумма значений поля health:",
            sum, null,
            request.getClientId()
        );
    }

    @Override
    public String getDescription() {
        return "sum_of_health : вывести сумму значений поля health для всех элементов коллекции";
    }
}
