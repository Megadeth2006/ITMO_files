package itmo.programming.server.manager;

import itmo.programming.common.user.User;
import itmo.programming.server.database.UserDaO;
import itmo.programming.server.hasher.PasswordHasher;
import java.security.NoSuchAlgorithmException;

/**
 * Класс, реализующий регистрацию и вход пользователя.
 */
public class AuthManager {
    private final UserDaO userManager;

    /**
     * Конструктор.
     *
     * @param userManager userManager.
     */
    public AuthManager(UserDaO userManager) {
        this.userManager = userManager;
    }

    /**
     * Регистрация нового пользователя.
     *
     * @param user пользователь (логин + пароль)
     * @return true, если регистрация прошла успешно, false если пользователь уже существует
     */
    public boolean register(User user) throws NoSuchAlgorithmException {
        if (userManager.exists(user.getUsername())) {
            return false;
        }

        final String salt = PasswordHasher.generateSalt();
        final String hash = PasswordHasher.hashPassword(user.getPassword(), salt);

        return userManager.insertUser(user.getUsername(), hash, salt);
    }

    /**
     * Вход в систему (аутентификация).
     *
     * @param user пользователь (логин + пароль)
     * @return true, если логин и пароль совпадают
     */
    public boolean login(User user) throws NoSuchAlgorithmException {
        final String salt = userManager.getSaltByUsername(user.getUsername());
        if (salt == null) {
            return false;
        }

        final String storedHash = userManager.getHashByUsername(user.getUsername());
        if (storedHash == null) {
            return false;
        }

        final String providedHash = PasswordHasher.hashPassword(user.getPassword(), salt);
        return providedHash.equals(storedHash);
    }

}
