package itmo.programming.server.auth;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.common.user.User;
import itmo.programming.server.commands.Command;
import itmo.programming.server.manager.AuthManager;
import java.security.NoSuchAlgorithmException;


/**
 * Класс для регистрации юзера.
 */
public class RegisterCommand implements Command {
    public final AuthManager authManager;

    /**
     * Конструктор.
     *
     * @param authManager authManager.
     */
    public RegisterCommand(AuthManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Response execute(Request request) throws NoSuchAlgorithmException {
        final User user = request.getUser();
        if (user == null) {
            return Response.error("Нет данных пользователя",
                    "MissingCregentials", user, request.getClientId());

        }
        final boolean success = authManager.register(user);
        if (success) {
            return Response.ok("Успешная регистрация", null, user, request.getClientId());

        } else {
            return Response.error(
                    "Регистрация не успешна, выберите другой username",
                    "RegisterFailed", user, request.getClientId());
        }
    }

    @Override
    public String getDescription() {
        return null;
    }
}
