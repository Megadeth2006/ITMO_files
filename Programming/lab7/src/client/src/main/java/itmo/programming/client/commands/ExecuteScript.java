package itmo.programming.client.commands;

import itmo.programming.client.manager.AuthManager;
import itmo.programming.client.manager.FileManager;
import itmo.programming.common.network.Request;
import itmo.programming.common.user.User;

/**
 * Команда executeScript на клиенте – формирует запрос без данных.
 */
public class ExecuteScript implements Base {
    private final AuthManager authManager;

    /**
     * Конструктор.
     *
     * @param authManager authManager
     */
    public ExecuteScript(final AuthManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Request execute(String[] args) {
        final User currentUser = authManager.getCurrentUser();
        if (args.length != 1) {
            System.out.println("Ошибка: Использование: execute_script <filename>");
            return null;
        }

        final String filename = args[0];
        final FileManager fileManager = new FileManager();
        final String[] data = fileManager.readCommandsFromFile(filename);
        if (data != null) {
            return new Request("execute_script",
                    args, data, currentUser, "client-" + System.currentTimeMillis());
        }
        return null;

    }
}
