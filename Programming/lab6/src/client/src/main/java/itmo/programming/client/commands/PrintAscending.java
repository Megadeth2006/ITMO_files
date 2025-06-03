package itmo.programming.client.commands;

import itmo.programming.common.network.Request;
import java.io.IOException;

/**
 * Команда print_ascending на клиенте – формирует запрос без данных.
 */
public class PrintAscending implements Base {
    @Override
    public Request execute(String[] args) throws IOException {
        return new Request("print_ascending", args, null, "client-" + System.currentTimeMillis());
    }
}
