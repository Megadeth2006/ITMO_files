package itmo.programming.client.commands;

import itmo.programming.common.network.Request;
import java.io.IOException;

/**
 * Команда show на клиенте – формирует запрос без данных.
 */
public class Show implements Base {
    @Override
    public Request execute(String[] args) throws IOException {
        return new Request("show", args, null, "client-" + System.currentTimeMillis());
    }
}
