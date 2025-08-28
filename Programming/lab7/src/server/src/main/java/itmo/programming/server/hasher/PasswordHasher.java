package itmo.programming.server.hasher;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Класс для хэширования паролей с использованием SHA-256.
 */
public class PasswordHasher {
    private static final int LENGTH = 16;
    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * Хэширует пароль с помощью алгоритма SHA-256.
     *
     * @param password пароль
     * @param salt соль, позволяющая одинаковые пароли закодировать уникально.
     * @return пароль хэшированный
     */
    public static String hashPassword(String password,
                                      String salt) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes()); // Добавляем соль
        final byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    /**
     * Сгенерировать соль.
     *
     * @return соль.
     */
    public static String generateSalt() {
        final byte[] salt = new byte[LENGTH];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
