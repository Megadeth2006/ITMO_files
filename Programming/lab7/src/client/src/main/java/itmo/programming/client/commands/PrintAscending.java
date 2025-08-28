package itmo.programming.client.commands;

import itmo.programming.client.manager.AuthManager;
import itmo.programming.common.network.Request;
import itmo.programming.common.user.User;
import java.io.IOException;

/**
 * Команда print_ascending на клиенте – формирует запрос без данных.
 */
public class PrintAscending implements Base {
    private final AuthManager authManager;

    /**
     * Конструктор.
     *
     * @param authManager authManager
     */
    public PrintAscending(final AuthManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Request execute(String[] args) throws IOException {
        final User currentUser = authManager.getCurrentUser();
        return new Request("print_ascending",
                args, null, currentUser,  "client-" + System.currentTimeMillis());
    }
}
