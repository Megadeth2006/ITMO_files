package itmo.programming.client.commands;

import itmo.programming.common.network.Request;
import java.io.IOException;

/**
 * Команда help на клиенте – формирует запрос без данных.
 */
public class Help implements Base {
    @Override
    public Request execute(String[] args) throws IOException {
        return new Request("help", args, null, "client-" + System.currentTimeMillis());
    }
}
