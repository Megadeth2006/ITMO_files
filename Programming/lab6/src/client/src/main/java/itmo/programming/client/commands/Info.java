package itmo.programming.client.commands;

import itmo.programming.common.network.Request;
import java.io.IOException;

/**
 * Команда info на клиенте – формирует запрос без данных.
 */
public class Info implements Base {
    @Override
    public Request execute(String[] args) throws IOException {
        return new Request("info", args, null, "client-" + System.currentTimeMillis());
    }
}
