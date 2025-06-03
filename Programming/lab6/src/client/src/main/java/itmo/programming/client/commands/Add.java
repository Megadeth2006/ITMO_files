package itmo.programming.client.commands;

import itmo.programming.client.manager.AskManager;
import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.network.Request;
import java.io.IOException;

/**
 * Команда add на клиенте – формирует запрос без данных.
 */
public class Add implements Base {
    private final AskManager askManager;

    /**
     * Конструктор.
     *
     * @param askManager askManager
     */
    public Add(AskManager askManager) {
        this.askManager = askManager;
    }

    @Override
    public Request execute(String[] args) throws IOException {

        final SpaceMarine spaceMarine = askManager.askSpaceMarine();
        return new Request("add", args, spaceMarine, "client-" + System.currentTimeMillis());

    }
}
