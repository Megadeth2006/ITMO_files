package itmo.programming.client.commands;

import itmo.programming.client.manager.AskManager;
import itmo.programming.client.manager.AuthManager;
import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.network.Request;
import itmo.programming.common.user.User;
import java.io.IOException;

/**
 * Команда add на клиенте – формирует запрос без данных.
 */
public class Add implements Base {
    private final AskManager askManager;
    private final AuthManager authManager;

    /**
     * Конструктор.
     *
     * @param askManager askManager
     */
    public Add(AskManager askManager, AuthManager authManager) {
        this.askManager = askManager;
        this.authManager = authManager;
    }

    @Override
    public Request execute(String[] args) throws IOException {
        final SpaceMarine spaceMarine = askManager.askSpaceMarine();
        final User currentUser = authManager.getCurrentUser();
        return new Request("add", args,
                spaceMarine, currentUser, "client-" + System.currentTimeMillis());

    }
}
