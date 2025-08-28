package itmo.programming.client.commands;

import itmo.programming.client.manager.AskManager;
import itmo.programming.client.manager.AuthManager;
import itmo.programming.client.manager.RequestSender;
import itmo.programming.common.model.SpaceMarine;
import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.common.user.User;
import java.io.IOException;

/**
 * Команда update на клиенте – формирует запрос без данных.
 */
public class Update implements Base {
    private final AskManager askManager;
    private final RequestSender requestSender;
    private final AuthManager authManager;

    /**
     * Конструктор.
     *
     * @param askManager askManager
     */
    public Update(final AskManager askManager,
                  RequestSender requestSender, AuthManager authManager) {
        this.askManager = askManager;
        this.requestSender = requestSender;
        this.authManager = authManager;
    }

    @Override
    public Request execute(String[] args) throws IOException {
        final User currentUser = authManager.getCurrentUser();
        if (args.length != 1) {
            System.out.println("Использование: update <id>");
            return null;
        }
        try {
            final int id = Integer.parseInt(args[0]);
            final String[] idArg = new String[]{String.valueOf(id)};
            final Request checkRequest = new Request("update",
                    idArg, "check", currentUser, "client-" + System.currentTimeMillis());
            final Response checkResponse = requestSender.send(checkRequest);
            if (!checkResponse.isSuccess()) {
                if (checkResponse.getMessage().equals("anotherOwner")) {
                    System.out.println("Элемент с id " + id + " принадлежит другому владельцу");
                    return null;
                }
                System.out.println("Элемента с таким id не существует");
                return null;
            }
            final SpaceMarine spaceMarine = askManager.askSpaceMarine();
            return new Request("update",
                    args, spaceMarine, currentUser, "client-" + System.currentTimeMillis());
        } catch (NumberFormatException e) {
            System.out.println("id должен быть числом");
            return null;
        }
    }



}
