package itmo.programming.client.commands;

import itmo.programming.common.network.Request;
import java.io.IOException;

/**
 * Команда filter_by_achievements на клиенте – формирует запрос с аргументом achievements.
 */
public class FilterByAchievements implements Base {
    @Override
    public Request execute(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Ошибка: Использование: filter_by_achievements <achievements>");
            return null;
        }

        return new Request("filter_by_achievements",
                args, null, "client-" + System.currentTimeMillis());
    }
}
