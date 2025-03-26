package itmo.programming.model;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

/**
 * Класс SpaceMarine модели.
 */
public class SpaceMarine implements Comparable<SpaceMarine> {
    private final int id;
    private final String name;
    private final Coordinates coordinates;
    private final LocalDateTime creationDate;
    private final Long health;
    private final String achievements;
    private final int height;
    private final MeleeWeapon meleeWeapon;
    private final Chapter chapter;

    /**
     * Конструктор класса.
     *
     * @param id id.
     *
     * @param name name.
     *
     * @param coordinates coordinates.
     *
     * @param creationDate creationDate.
     *
     * @param health health.
     *
     * @param achievements achievements.
     *
     * @param height height.
     *
     * @param meleeWeapon meleeWeapon.
     *
     * @param chapter chapter.
     */
    public SpaceMarine(int id, String name, Coordinates coordinates,
                       LocalDateTime creationDate, Long health,
                       String achievements, int height, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.achievements = achievements;
        this.height = height;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    /**
     * Получить значение поля name.
     */
    public String getName() {
        return name;
    }

    /**
     * Получить значение поля x.
     */
    public int getX() {
        return coordinates.getX();
    }

    /**
     * Получить значение поля y.
     */
    public double getY() {
        return coordinates.getY();
    }

    /**
     * Получить значение поля health.
     */
    public Long getHealth() {
        return health;
    }

    /**
     * Получить значение поля achievements.
     */
    public String getAchievements() {
        return achievements;
    }

    /**
     * Получить значение поля height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Получить значение поля chapter.
     */
    public String getChapterName() {
        return chapter.getChapterName();
    }

    /**
     * Получить значение поля MeleeWeapon.
     */
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    /**
     * Получить значение поля parentLegion.
     */
    public String getParentLegion() {
        return chapter.getParentLegion();
    }

    @Override
    public String toString() {
        return "SpaceMarine: (id: "
                + id
                + " | name: "
                + name
                + " | coordinates: "
                + coordinates
                + " | creationDate: "
                + creationDate
                + " | health: "
                + health
                + " | achievements: "
                + achievements
                + " | height: "
                + height
                + " | meleeWeapon: " + meleeWeapon
                + " | chapter: " + chapter + ")";
    }

    /**
     * Получить id объекта модели.
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final SpaceMarine spacemarine = (SpaceMarine) obj;
        return Objects.equals(id, spacemarine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate,
                health, achievements, height, meleeWeapon, chapter);
    }

    @Override
    public int compareTo(SpaceMarine object) {
        return Comparator.comparingInt(SpaceMarine::getId).compare(this, object);
    }

}
