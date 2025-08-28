package itmo.programming.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс для взаимодействия с таблицей users в базе данных PostgreSQL.
 */
public class UserDaO {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    private final Connection connection;

    /**
     * Конструктор.
     *
     * @param connection connection.
     */
    public UserDaO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Проверяет, существует ли пользователь с таким именем.
     *
     * @param username имя пользователя
     * @return true, если пользователь найден
     */
    public boolean exists(String username) {
        final String sql = "SELECT 1 FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(ONE, username);
            final ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            //
            return false;
        }
    }

    /**
     * Вставляет нового пользователя.
     *
     * @param username имя
     * @param passwordHash хэш пароля
     * @param salt соль
     * @return true, если пользователь успешно добавлен
     */
    public boolean insertUser(String username, String passwordHash, String salt) {
        final String sql = "INSERT INTO users (username, password_hash, salt) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(ONE, username);
            stmt.setString(TWO, passwordHash);
            stmt.setString(THREE, salt);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Получает соль пользователя по имени.
     *
     * @param username имя пользователя
     * @return соль или null
     */
    public String getSaltByUsername(String username) {
        final String sql = "SELECT salt FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(ONE, username);
            final ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getString("salt") : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Получает хэш пароля по имени пользователя.
     *
     * @param username имя
     * @return хэш или null
     */
    public String getHashByUsername(String username) {
        final String sql = "SELECT password_hash FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(ONE, username);
            final ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getString("password_hash") : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
