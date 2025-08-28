package itmo.programming.common.user;

import java.io.Serializable;

/**
 * Класс для хранения логина и пароля юзера.
 */
public class User implements Serializable {
    private final String username;
    private final String password;

    /**
     * Конструктор.
     *
     * @param username username.
     * @param password password.
     */
    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Получить username.
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Получить пароль.
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }
}
