package itmo.programming.model;

/**
 * Класс Chapter модели.
 */
public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String parentLegion;

    /**
     * Конструктор класса.
     *
     * @param name name.
     *
     * @param parentLegion parentLegion.
     */
    public Chapter(String name, String parentLegion) {
        this.name = name;
        this.parentLegion = parentLegion;
    }

    /**
     * Получить значение поля name.
     */
    public String getChapterName() {
        return name;
    }

    /**
     * Получить значение поля parentLegion.
     */
    public String getParentLegion() {
        return parentLegion;
    }

    @Override
    public String toString() {
        return "(name: " + name + "; parentLegion: " + parentLegion + ")";
    }
}
