package itmo.programming.client.commands;

import itmo.programming.common.network.Request;
import java.io.IOException;

/**
 * Команда sum_of_health на клиенте – формирует запрос без данных.
 */
public class SumOfHealth implements Base {
    @Override
    public Request execute(String[] args) throws IOException {
        return new Request("sum_of_health", args, null, "client-" + System.currentTimeMillis());
    }
}
