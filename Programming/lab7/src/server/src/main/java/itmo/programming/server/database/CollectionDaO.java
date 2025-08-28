package itmo.programming.server.database;

import itmo.programming.common.model.SpaceMarine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.TreeSet;

/**
 * Класс обеспечивает взаимодействие с базой данных для реализации коллекции.
 */
public class CollectionDaO {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;
    private static final int TEN = 10;
    private static final int ELEVEN = 11;
    private final Connection connection;

    /**
     * Конструктор.
     *
     * @param connection connection.
     */
    public CollectionDaO(final Connection connection) {
        this.connection = connection;

    }

    /**
     * Загрузить все элементы коллекции из базы данных.
     *
     * @return treeset
     */
    public TreeSet<SpaceMarine> loadAll() {
        final TreeSet<SpaceMarine> collection = new TreeSet<>();
        final String sql = """
            select
                c.*,
                coord.x,
                coord.y,
                ch.name as chapter_name,
                ch.parent_legion as chapter_parent_legion
            from collection c
            join coordinates coord on c.coordinates_id = coord.id
            join chapter ch on c.chapter_id = ch.id
            """;

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                final SpaceMarine spaceMarine = DatabaseHelper.constructSpaceMarine(resultSet);
                collection.add(spaceMarine);
            }
            return collection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Добавить элемент в коллекцию.
     *
     * @param spaceMarine spaceMarine
     * @param owner owner
     * @return int
     * @throws SQLException исключение
     */
    public int insert(final SpaceMarine spaceMarine,
                      String owner) throws SQLException, NullPointerException {
        final String sqlCoordinates = "insert into coordinates(x, y) values(?,?) returning id";
        final String sqlChapter = """
                insert into chapter(
                name, parent_legion) values(?,?) returning id
                """;
        final String sqlCollection = """
                insert into collection (
                name, creation_date, health, achievements, height,
                melee_weapon, owner, coordinates_id, chapter_id
                ) values (?, ?, ?, ?, ?, ?, ?, ?, ?)
                returning id""";

        try {
            final int coordinatesId;
            try (PreparedStatement statement = connection.prepareStatement(sqlCoordinates)) {
                statement.setInt(1, spaceMarine.getCoordinates().getX());
                statement.setInt(2, spaceMarine.getCoordinates().getY());
                final ResultSet rs = statement.executeQuery();
                rs.next();
                coordinatesId = rs.getInt("id");
            }

            final int chapterId;
            try (PreparedStatement statement = connection.prepareStatement(sqlChapter)) {
                statement.setString(1, spaceMarine.getChapter().getName());
                statement.setString(2, spaceMarine.getChapter().getParentLegion());
                final ResultSet rs = statement.executeQuery();
                rs.next();
                chapterId = rs.getInt("id");
            }

            try (PreparedStatement statement = connection.prepareStatement(sqlCollection)) {
                statement.setString(ONE, spaceMarine.getName());
                statement.setTimestamp(TWO, Timestamp.valueOf(spaceMarine.getCreationDate()));
                statement.setDouble(THREE, spaceMarine.getHealth());
                statement.setString(FOUR, spaceMarine.getAchievements());
                statement.setFloat(FIVE, spaceMarine.getHeight());
                statement.setString(SIX, spaceMarine.getMeleeWeapon().name());
                statement.setString(SEVEN, owner);
                statement.setInt(EIGHT, coordinatesId);
                statement.setInt(NINE, chapterId);
                final ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new SQLException("Ошибка при вставке - не удалось получить id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Проверить на владение элемента юзером.
     *
     * @param id id
     * @param owner owner
     * @return boolean
     * @throws SQLException sqlexception
     */
    public boolean checkOwnership(int id, String owner) throws SQLException {
        final String sql = "SELECT COUNT(*) FROM collection WHERE id = ? AND owner = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(ONE, id);
            statement.setString(TWO, owner);
            final ResultSet rs = statement.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    /**
     * Обновить элемент коллекции в базе данных.
     *
     * @param id          ID элемента
     * @param spaceMarine Новые данные
     * @param owner       Владелец
     * @return true, если обновление прошло успешно
     * @throws SQLException если произошла ошибка БД
     */
    public boolean update(int id,
                          final SpaceMarine spaceMarine,
                          final String owner) throws SQLException {
        if (!checkOwnership(id, owner)) {
            return false;
        }

        final String getCoordinatesId = """
            SELECT coordinates_id 
            FROM collection
            WHERE id = ? AND owner = ?
            """;
        final String getChapterId = "SELECT chapter_id FROM collection WHERE id = ? AND owner = ?";

        final String updateCoordinates = "UPDATE coordinates SET x = ?, y = ? WHERE id = ?";
        final String updateChapter = "UPDATE chapter SET name = ?, parent_legion = ? WHERE id = ?";
        final String updateCollection = """
            UPDATE collection SET
                name = ?, creation_date = ?, health = ?, achievements = ?, height = ?,
                melee_weapon = ?, coordinates_id = ?, chapter_id = ?
            WHERE id = ? AND owner = ?
            """;

        try {
            final int coordinatesId;
            final int chapterId;

            // Получаем текущие ID координат и главы
            try (PreparedStatement stmt = connection.prepareStatement(getCoordinatesId)) {
                stmt.setInt(1, id);
                stmt.setString(2, owner);
                final ResultSet rs = stmt.executeQuery();
                rs.next();
                coordinatesId = rs.getInt("coordinates_id");
            }

            try (PreparedStatement stmt = connection.prepareStatement(getChapterId)) {
                stmt.setInt(1, id);
                stmt.setString(2, owner);
                final ResultSet rs = stmt.executeQuery();
                rs.next();
                chapterId = rs.getInt("chapter_id");
            }

            // Обновляем координаты
            try (PreparedStatement stmt = connection.prepareStatement(updateCoordinates)) {
                stmt.setInt(ONE, spaceMarine.getCoordinates().getX());
                stmt.setInt(TWO, spaceMarine.getCoordinates().getY());
                stmt.setInt(THREE, coordinatesId);
                stmt.executeUpdate();
            }

            // Обновляем главу
            try (PreparedStatement stmt = connection.prepareStatement(updateChapter)) {
                stmt.setString(ONE, spaceMarine.getChapter().getName());
                stmt.setString(TWO, spaceMarine.getChapter().getParentLegion());
                stmt.setInt(THREE, chapterId);
                stmt.executeUpdate();
            }

            // Обновляем сам элемент в коллекции
            try (PreparedStatement stmt = connection.prepareStatement(updateCollection)) {
                stmt.setString(ONE, spaceMarine.getName());
                stmt.setTimestamp(TWO, Timestamp.valueOf(spaceMarine.getCreationDate()));
                stmt.setDouble(THREE, spaceMarine.getHealth());
                stmt.setString(FOUR, spaceMarine.getAchievements());
                stmt.setFloat(FIVE, spaceMarine.getHeight());
                stmt.setString(SIX, spaceMarine.getMeleeWeapon().name());
                stmt.setInt(SEVEN, coordinatesId);
                stmt.setInt(EIGHT, chapterId);
                stmt.setInt(NINE, id);
                stmt.setString(TEN, owner);

                return stmt.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Удалить по id элемент коллекции из базы данных.
     *
     * @param id id
     * @param owner owner
     * @return boolean
     * @throws SQLException sqlexception
     */
    public boolean deleteById(final int id, String owner) throws SQLException {
        final String getIdsSql = """
            SELECT coordinates_id, chapter_id FROM collection WHERE id = ? AND owner = ?
            """;
        final String deleteCollectionSql = "DELETE FROM collection WHERE id = ? AND owner = ?";
        final String deleteCoordinatesSql = "DELETE FROM coordinates WHERE id = ?";
        final String deleteChapterSql = "DELETE FROM chapter WHERE id = ?";

        try {
            Integer coordinatesId = null;
            Integer chapterId = null;

            // Получаем IDs перед удалением
            try (PreparedStatement statement = connection.prepareStatement(getIdsSql)) {
                statement.setInt(ONE, id);
                statement.setString(TWO, owner);
                final ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    coordinatesId = rs.getObject("coordinates_id", Integer.class);
                    chapterId = rs.getObject("chapter_id", Integer.class);
                } else {
                    return false; // Запись не найдена
                }
            }
            
            try (PreparedStatement statement = connection.prepareStatement(deleteCollectionSql)) {
                statement.setInt(ONE, id);
                statement.setString(TWO, owner);
                if (statement.executeUpdate() == 0) {
                    return false;
                }
            }
            if (coordinatesId != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        deleteCoordinatesSql)) {
                    statement.setInt(1, coordinatesId);
                    statement.executeUpdate();
                }
            }
            
            if (chapterId != null) {
                try (PreparedStatement statement = connection.prepareStatement(deleteChapterSql)) {
                    statement.setInt(1, chapterId);
                    statement.executeUpdate();
                }
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Удалить все элементы коллекции из базы данных.
     *
     * @return boolean
     * @throws SQLException исключение.
     */
    public boolean deleteAll(String owner) throws SQLException {
        final String getIdsSql = """
            SELECT coordinates_id, chapter_id 
            FROM collection 
            WHERE owner = ?
            """;

        final String deleteCollectionSql = """
            DELETE FROM collection 
            WHERE owner = ?
            """;

        final String checkCoordinatesUsage = """
            SELECT COUNT(*) 
            FROM collection 
            WHERE coordinates_id = ?
            """;

        final String deleteCoordinatesSql = """
            DELETE FROM coordinates 
            WHERE id = ?
            """;

        final String checkChapterUsage = """
            SELECT COUNT(*) 
            FROM collection 
            WHERE chapter_id = ?
            """;

        final String deleteChapterSql = """
            DELETE FROM chapter 
            WHERE id = ?
            """;

        try {
            final TreeSet<Integer> coordinateIds = new TreeSet<>();
            final TreeSet<Integer> chapterIds = new TreeSet<>();

            try (PreparedStatement statement = connection.prepareStatement(getIdsSql)) {
                statement.setString(1, owner);
                final ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    final Integer coordId = rs.getObject("coordinates_id", Integer.class);
                    final Integer chapId = rs.getObject("chapter_id", Integer.class);
                    if (coordId != null) {
                        coordinateIds.add(coordId);
                    }
                    if (chapId != null) {
                        chapterIds.add(chapId);
                    }
                }
            }

            try (PreparedStatement statement = connection.prepareStatement(deleteCollectionSql)) {
                statement.setString(1, owner);
                final int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted == 0) {
                    return false;
                }
            }

            for (Integer id : coordinateIds) {
                try (PreparedStatement statement = connection.prepareStatement(
                        checkCoordinatesUsage)) {
                    statement.setInt(1, id);
                    final ResultSet rs = statement.executeQuery();
                    rs.next();
                    if (rs.getInt(1) == 0) {
                        try (PreparedStatement delStmt = connection.prepareStatement(
                                deleteCoordinatesSql)) {
                            delStmt.setInt(1, id);
                            delStmt.executeUpdate();
                        }
                    }
                }
            }

            for (Integer id : chapterIds) {
                try (PreparedStatement statement = connection.prepareStatement(
                        checkChapterUsage)) {
                    statement.setInt(1, id);
                    final ResultSet rs = statement.executeQuery();
                    rs.next();
                    if (rs.getInt(1) == 0) {
                        try (PreparedStatement delStmt = connection.prepareStatement(
                                deleteChapterSql)) {
                            delStmt.setInt(1, id);
                            delStmt.executeUpdate();
                        }
                    }
                }
            }

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
