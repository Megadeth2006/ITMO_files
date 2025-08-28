package itmo.programming.client.commands;

import itmo.programming.client.manager.AuthManager;
import itmo.programming.common.network.Request;
import itmo.programming.common.user.User;
import java.io.IOException;

/**
 * Команда removeById на клиенте – формирует запрос без данных.
 */
public class RemoveById implements Base {
    private final AuthManager authManager;

    /**
     *  Конструктор.
     *
     * @param authManager authManager.
     */
    public RemoveById(final AuthManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Request execute(String[] args) throws IOException {
        final User currentUser = authManager.getCurrentUser();
        if (args.length != 1) {
            System.out.println("Ошибка: Использование: remove_by_id <id>");
            return null;
        }

        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: id должен быть числом");
            return null;
        }

        return new Request("remove_by_id",
                args, null, currentUser, "client-" + System.currentTimeMillis());
    }
}
