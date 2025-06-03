package itmo.programming.client.commands;

import itmo.programming.client.manager.AskManager;
import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.network.Request;
import java.io.IOException;

/**
 * Команда addIfMin на клиенте – формирует запрос без данных.
 */
public class AddIfMin implements Base {
    private final AskManager askManager;

    /**
     * Конструктор.
     *
     * @param askManager askManager
     */
    public AddIfMin(AskManager askManager) {
        this.askManager = askManager;
    }

    @Override
    public Request execute(String[] args) throws IOException {
        final SpaceMarine spaceMarine = askManager.askSpaceMarine();
        return new Request("add_if_min", args, spaceMarine, "client-" + System.currentTimeMillis());
    }
}
