package itmo.programming.client.commands;

import itmo.programming.client.manager.AuthManager;
import itmo.programming.common.network.Request;
import itmo.programming.common.user.User;
import java.io.IOException;

/**
 * Команда filter_by_achievements на клиенте – формирует запрос с аргументом achievements.
 */
public class FilterByAchievements implements Base {
    private final AuthManager authManager;

    /**
     * Конструктор.
     *
     * @param authManager authManager.
     */
    public FilterByAchievements(AuthManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Request execute(String[] args) throws IOException {
        final User currentUser = authManager.getCurrentUser();
        if (args.length != 1) {
            System.out.println("Ошибка: Использование: filter_by_achievements <achievements>");
            return null;
        }

        return new Request("filter_by_achievements",
                args, null, currentUser,  "client-" + System.currentTimeMillis());
    }
}
