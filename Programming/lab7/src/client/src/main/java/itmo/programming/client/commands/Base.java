package itmo.programming.client.commands;

import itmo.programming.common.network.Request;
import java.io.IOException;

/**
 * Интерфейс.
 */
public interface Base {

    /**
     * Метод execute.
     *
     * @param args аргументы
     */
    Request execute(String[] args) throws IOException;
}
