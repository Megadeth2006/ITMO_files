package itmo.programming.client.auth;

import itmo.programming.client.commands.Base;
import itmo.programming.client.manager.AuthManager;
import itmo.programming.client.manager.ConsoleInputManager;
import itmo.programming.common.network.Request;
import itmo.programming.common.user.User;
import java.io.IOException;

/**
 * Команда login для формирования запроса серверу.
 */
public class Login implements Base {
    private static User currentUser;
    private ConsoleInputManager inputManager;
    private final AuthManager authManager;

    /**
     * Конструктор.
     *
     * @param inputManager inputManager
     * @param authManager authManager
     */
    public Login(ConsoleInputManager inputManager, AuthManager authManager) {
        this.inputManager = inputManager;
        this.authManager = authManager;
    }

    @Override
    public Request execute(String[] args) throws IOException {

        if (authManager.isAuthorized()) {
            System.out.println("Вы уже авторизованы!");
            return null;
        }
        System.out.println("Вход в систему");
        System.out.println("Введите имя пользователя:");
        final String username = inputManager.readLine();
        if (username == null || username.equalsIgnoreCase("exit")) {
            System.exit(0);
        }
        
        System.out.println("Введите пароль:");
        final String password = inputManager.readLine();
        if (password == null || password.equalsIgnoreCase("exit")) {
            System.exit(0);
        }

        currentUser = new User(username, password);
        return new Request("login", null,
                null, currentUser, "client-" + System.currentTimeMillis());
    }
}
