package itmo.programming.server.auth;

import itmo.programming.common.network.Request;
import itmo.programming.common.network.Response;
import itmo.programming.common.user.User;
import itmo.programming.server.commands.Command;
import itmo.programming.server.manager.AuthManager;
import java.security.NoSuchAlgorithmException;

/**
 * Команда для входа в аккаунт.
 */
public class LoginCommand implements Command {
    private final AuthManager authManager;

    /**
     * Конструктор.
     *
     * @param authManager authManager
     */
    public LoginCommand(AuthManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Response execute(Request request) throws NoSuchAlgorithmException {
        final User user = request.getUser();
        if (user == null) {
            return Response.error(
                    "Нет данных пользователя", "MissingCredentials", user, request.getClientId());
        }
        final boolean success = authManager.login(user);
        if (success) {
            return Response.ok("Успешный вход", null, user, request.getClientId());
        } else {
            return Response.error(
                    "Неверное имя пользователя или пароль",
                    "LoginFailed", user, request.getClientId());
        }
    }


    @Override
    public String getDescription() {
        return null;
    }
}
