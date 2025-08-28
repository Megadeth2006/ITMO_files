package itmo.programming.client.manager;

import itmo.programming.common.user.User;

/**
 * Менеджер авторизации на клиенте.
 */
public class AuthManager {
    private static User currentUser;

    /**
     * Конструктор.
     */
    public AuthManager() {
        this.currentUser = null;
    }

    /**
     * Авторизация пользователя.
     *
     * @param user юзер
     */
    public static void authorize(final User user) {
        currentUser = user;
    }

    /**
     * Проверка на авторизованность.
     *
     * @return boolean
     */
    public boolean isAuthorized() {
        return currentUser != null;
    }

    /**
     * Получить текущего юзера.
     *
     * @return user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Выйти из авторизации.
     */
    public static void logout() {
        currentUser = null;
    }
}
