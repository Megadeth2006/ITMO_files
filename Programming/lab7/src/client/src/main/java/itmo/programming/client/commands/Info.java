package itmo.programming.client.commands;

import itmo.programming.client.manager.AuthManager;
import itmo.programming.common.network.Request;
import itmo.programming.common.user.User;
import java.io.IOException;

/**
 * Команда info на клиенте – формирует запрос без данных.
 */
public class Info implements Base {
    private final AuthManager authManager;

    /**
     * Конструктор.
     *
     * @param authManager authManager.
     */
    public Info(final AuthManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Request execute(String[] args) throws IOException {
        final User currentUser = authManager.getCurrentUser();
        return new Request("info", args, null, currentUser, "client-" + System.currentTimeMillis());
    }
}
