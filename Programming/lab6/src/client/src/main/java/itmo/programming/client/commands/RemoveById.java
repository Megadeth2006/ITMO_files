package itmo.programming.client.commands;

import itmo.programming.common.network.Request;
import java.io.IOException;

/**
 * Команда removeById на клиенте – формирует запрос без данных.
 */
public class RemoveById implements Base {
    @Override
    public Request execute(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Ошибка: Использование: remove_by_id <id>");
            return null;
        }

        try {
            Integer.parseInt(args[0]); // Проверка на число
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: id должен быть числом");
            return null;
        }

        return new Request("remove_by_id", args, null, "client-" + System.currentTimeMillis());
    }
}
