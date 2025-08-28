package itmo.programming.client.manager;

import itmo.programming.client.core.Client;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import java.io.IOException;

/**
 * Класс для отправки запросов клиентом и получения ответов.
 */
public class RequestSender {
    private final Client client;

    /**
     * Конструктор.
     *
     * @param client клиент, который отправляет запросы на сервер
     */
    public RequestSender(Client client) {
        this.client = client;
    }

    /**
     * Отправляет запрос и возвращает ответ.
     *
     * @param request запрос на сервер
     * @return ответ сервера
     * @throws IOException при ошибке отправки
     */
    public Response send(Request request) throws IOException {
        return client.sendRequest(request);
    }
}
