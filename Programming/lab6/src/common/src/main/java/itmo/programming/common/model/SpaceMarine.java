package itmo.programming.common.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

/**
 * Класс, представляющий космического десантника.
 */
public class SpaceMarine implements Comparable<SpaceMarine>, Serializable {

    // Поле не может быть null, Значение поля должно быть больше 0,
    // Значение этого поля должно быть уникальным,
    // Значение этого поля должно генерироваться автоматически
    private Integer id;
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private LocalDateTime creationDate;
    private double health; // Значение поля должно быть больше 0
    private String achievements; // Поле не может быть null
    private float height;
    private MeleeWeapon meleeWeapon; // Поле не может быть null
    private Chapter chapter; // Поле может быть null

    /**
     * Пустой конструктор для GSON.
     */
    public SpaceMarine() {
    }

    /**
     * Конструктор для создания космического десантника.
     *
     * @param id идентификатор
     * @param name имя
     * @param coordinates координаты
     * @param creationDate дата создания
     * @param health здоровье
     * @param achievements достижения
     * @param height рост
     * @param meleeWeapon оружие ближнего боя
     * @param chapter орден
     */
    public SpaceMarine(Integer id, String name, Coordinates coordinates,
            LocalDateTime creationDate, double health,
            String achievements, float height,
            MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate != null ? creationDate : LocalDateTime.now();
        this.health = health;
        this.achievements = achievements;
        this.height = height;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    /**
     * Получить идентификатор.
     *
     * @return идентификатор
     */
    public Integer getId() {
        return id;
    }

    /**
     * Установить идентификатор.
     *
     * @param id идентификатор
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Получить имя.
     *
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Установить имя.
     *
     * @param name имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить координаты.
     *
     * @return координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Установить координаты.
     *
     * @param coordinates координаты
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Получить дату создания.
     *
     * @return дата создания
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Установить дату создания.
     *
     * @param creationDate дата создания
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Получить здоровье.
     *
     * @return здоровье
     */
    public double getHealth() {
        return health;
    }

    /**
     * Установить здоровье.
     *
     * @param health здоровье
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * Получить достижения.
     *
     * @return достижения
     */
    public String getAchievements() {
        return achievements;
    }

    /**
     * Установить достижения.
     *
     * @param achievements достижения
     */
    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    /**
     * Получить рост.
     *
     * @return рост
     */
    public float getHeight() {
        return height;
    }

    /**
     * Установить рост.
     *
     * @param height рост
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Получить расстояние от начала координат.
     *
     * @return расстояние
     */
    public Double getDistance() {
        return this.coordinates.distance();
    }

    /**
     * Получить оружие ближнего боя.
     *
     * @return оружие ближнего боя
     */
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    /**
     * Установить оружие ближнего боя.
     *
     * @param meleeWeapon оружие ближнего боя
     */
    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    /**
     * Получить орден.
     *
     * @return орден
     */
    public Chapter getChapter() {
        return chapter;
    }

    /**
     * Установить орден.
     *
     * @param chapter орден
     */
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SpaceMarine that = (SpaceMarine) o;
        
        // Сравниваем по всем полям, кроме id
        return Double.compare(that.health, health) == 0
                && Float.compare(that.height, height) == 0
                && Objects.equals(name, that.name)
                && Objects.equals(coordinates, that.coordinates)
                && Objects.equals(achievements, that.achievements)
                && meleeWeapon == that.meleeWeapon
                && Objects.equals(chapter, that.chapter);
    }

    @Override
    public int hashCode() {
        // Генерируем хеш-код по всем полям, кроме id
        return Objects.hash(name, coordinates, creationDate, health,
                achievements, height, meleeWeapon, chapter);
    }

    @Override
    public String toString() {
        return "SpaceMarine{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", coordinates=" + coordinates
                + ", creationDate=" + creationDate
                + ", health=" + health
                + ", achievements='" + achievements + '\''
                + ", height=" + height
                + ", meleeWeapon=" + meleeWeapon
                + ", chapter=" + chapter
                + '}';
    }

    @Override
    public int compareTo(SpaceMarine o) {
        if (o == null) {
            return 1;
        }
        return Comparator
                .comparing(SpaceMarine::getName, Comparator.nullsFirst(String::compareTo))
                .thenComparing(SpaceMarine::getId, Comparator.nullsFirst(Integer::compareTo))
                .compare(this, o);
    }
}
