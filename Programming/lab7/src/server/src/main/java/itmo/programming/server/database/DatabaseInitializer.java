package itmo.programming.server.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Класс для инициализации базы данных.
 */
public class DatabaseInitializer {
    private static final Logger logger = Logger.getLogger(DatabaseInitializer.class.getName());
    private final Connection connection;

    /**
     * Конструктор.
     *
     * @param connection connection.
     */
    public DatabaseInitializer(Connection connection) {
        this.connection = connection;
    }

    /**
     * Инициализирует базу данных, создавая необходимые таблицы и индексы.
     */
    public void initializeDatabase() {
        try {
            final String sql = loadSqlFromResource();
            executeSql(sql);
            logger.info("База данных успешно инициализирована");
        } catch (IOException | SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при инициализации базы данных", e);
            throw new RuntimeException("Не удалось инициализировать базу данных", e);
        }
    }

    private String loadSqlFromResource() throws IOException {
        final String sqlFilePath = "init.sql";
        final File file = new File(sqlFilePath);

        if (!file.exists()) {
            throw new IOException("Файл SQL не найден: " + sqlFilePath);
        }
        return Files.lines(Paths.get(sqlFilePath))
                .collect(Collectors.joining("\n"));
    }

    private void executeSql(String sql) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }
}
