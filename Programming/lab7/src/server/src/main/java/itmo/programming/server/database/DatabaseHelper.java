package itmo.programming.server.database;

import itmo.programming.common.model.Chapter;
import itmo.programming.common.model.Coordinates;
import itmo.programming.common.model.MeleeWeapon;
import itmo.programming.common.model.SpaceMarine;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс, конвертирующий spaceMarine.
 */
public class DatabaseHelper {

    /**
     * Собрать spaceMarine.
     *
     * @param rs rs.
     * @return SpaceMarine
     * @throws SQLException исключение.
     */
    public static SpaceMarine constructSpaceMarine(ResultSet rs) throws SQLException {
        final SpaceMarine spaceMarine = new SpaceMarine();

        final Coordinates coordinates = new Coordinates();
        spaceMarine.setCoordinates(coordinates);
        coordinates.setX(rs.getInt("x"));
        coordinates.setY(rs.getInt("y"));


        final Chapter chapter = new Chapter();
        chapter.setName(rs.getString("chapter_name"));
        chapter.setParentLegion(rs.getString("chapter_parent_legion"));

        final String weaponStr = rs.getString("melee_weapon");
        final MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(weaponStr);
        spaceMarine.setMeleeWeapon(meleeWeapon);

        spaceMarine.setId(rs.getInt("id"));
        spaceMarine.setName(rs.getString("name"));
        spaceMarine.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
        spaceMarine.setHealth(rs.getDouble("health"));
        spaceMarine.setAchievements(rs.getString("achievements"));
        spaceMarine.setHeight(rs.getFloat("height"));
        spaceMarine.setChapter(chapter);
        spaceMarine.setOwner(rs.getString("owner"));
        return spaceMarine;
    }
}
