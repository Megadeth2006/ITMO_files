package itmo.programming.client.commands;

import itmo.programming.client.manager.AskManager;
import itmo.programming.client.manager.RequestSender;
import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import java.io.IOException;

/**
 * Команда update на клиенте – формирует запрос без данных.
 */
public class Update implements Base {
    private final AskManager askManager;
    private final RequestSender requestSender;

    /**
     * Конструктор.
     *
     * @param askManager askManager
     */
    public Update(final AskManager askManager, RequestSender requestSender) {
        this.askManager = askManager;
        this.requestSender = requestSender;
    }

    @Override
    public Request execute(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Использование: update <id>");
            return null;
        }
        try {
            final int id = Integer.parseInt(args[0]);
            final String[] idArg = new String[]{String.valueOf(id)};
            final Request checkRequest = new Request("update",
                    idArg, "check", "client-" + System.currentTimeMillis());
            final Response checkResponse = requestSender.send(checkRequest);
            if (!checkResponse.isSuccess()) {
                System.out.println("Элемента с таким id не существует");
                return null;
            }
            final SpaceMarine spaceMarine = askManager.askSpaceMarine();
            return new Request("update",
                    args, spaceMarine, "client-" + System.currentTimeMillis());
        } catch (NumberFormatException e) {
            System.out.println("id должен быть числом");
            return null;
        }
    }



}
