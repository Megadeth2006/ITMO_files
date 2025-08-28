package itmo.programming.client.request;

import itmo.programming.common.network.Response;

/**
 * Класс для отображения ответа сервера в консоли.
 */
public class RequestDisplay {


    /**
     * Отображает содержимое ответа с сервера.
     *
     * @param response ответ.
     */
    public static void displayResponse(Response response) {
        if (response == null) {
            System.out.println("Сервер недоступен.");
            return;
        }

        if (response.isSuccess()) {
            System.out.println("Успешно: " + response.getMessage());
            if (response.getData() != null) {
                System.out.println("Данные: " + response.getData());
            }
        } else {
            System.out.println("Ошибка: " + response.getMessage());
        }
    }


}
