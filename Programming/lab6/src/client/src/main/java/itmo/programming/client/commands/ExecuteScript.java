package itmo.programming.client.commands;

import itmo.programming.client.manager.FileManager;
import itmo.programming.common.network.Request;

/**
 * Команда executeScript на клиенте – формирует запрос без данных.
 */
public class ExecuteScript implements Base {
    @Override
    public Request execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Ошибка: Использование: execute_script <filename>");
            return null;
        }

        final String filename = args[0];
        final FileManager fileManager = new FileManager();

        final String[] data = fileManager.readCommandsFromFile(filename);
        if (data != null) {
            return new Request("execute_script",
                    args, data, "client-" + System.currentTimeMillis());
        }
        return null;

    }
}
