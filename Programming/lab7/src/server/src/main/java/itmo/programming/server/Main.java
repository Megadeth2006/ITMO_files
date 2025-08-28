package itmo.programming.server;

import itmo.programming.server.core.Server;
import itmo.programming.server.database.DatabaseInitializer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Главный класс сервера.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final int PORT = 6789;
    private static final String DB_URL = "jdbc:postgresql://pg:5432/studs";
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");


    public static void main(String[] args) {
        if (DB_USER == null || DB_PASSWORD == null) {
            logger.severe(
                    "Необходимо установить переменные окружения DB_USER и DB_PASSWORD");
            System.exit(0);
        }


        logger.info("Подключение к базе данных PostgreSQL...");
        try {
            final Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Успешное подключение к базе.");

            // Инициализация базы данных
            final DatabaseInitializer initializer = new DatabaseInitializer(connection);
            initializer.initializeDatabase();

            // Запуск сервера
            final Server server = new Server(PORT, connection);
            server.start();
        } catch (final SQLException e) {
            logger.severe("Пробросьте порт!");
            System.exit(0);
        }
    }
}
