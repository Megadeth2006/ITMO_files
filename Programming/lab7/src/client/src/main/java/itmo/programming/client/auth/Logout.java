package itmo.programming.client.auth;

import itmo.programming.client.commands.Base;
import itmo.programming.client.manager.AuthManager;
import itmo.programming.common.network.Request;
import java.io.IOException;

/**
 * Класс, реализующий выход из аккаунта.
 */
public class Logout implements Base {
    private final AuthManager authManager;

    /**
     * Конструктор.
     *
     * @param authManager authManager
     */
    public Logout(final AuthManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Request execute(String[] args) throws IOException {
        if (!authManager.isAuthorized()) {
            System.out.println("Вы не авторизованы!");
            return null;
        }

        AuthManager.logout();
        System.out.println("Выполнен выход!");
        System.out.println("Введите 'login' или 'register'");
        return null;
    }
}
