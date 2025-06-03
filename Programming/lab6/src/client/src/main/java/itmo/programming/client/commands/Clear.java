package itmo.programming.client.commands;

import itmo.programming.common.network.Request;
import java.io.IOException;

/**
 * Команда clear на клиенте – формирует запрос без данных.
 */
public class Clear implements Base {

    @Override
    public Request execute(String[] args) throws IOException {
        return new Request("clear", args, null, "client-" + System.currentTimeMillis());
    }
}

