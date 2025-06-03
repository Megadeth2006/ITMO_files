package itmo.programming.common.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс Chapter, представляющий космический орден.
 */
public class Chapter implements Serializable {
    
    private String name; // Поле не может быть null, Строка не может быть пустой
    private String parentLegion;

    /**
     * Пустой конструктор для GSON.
     */
    public Chapter() {
    }

    /**
     * Конструктор для создания нового Chapter.name.
     *
     * @param name Chapter.name
     * @param parentLegion parentLegion
     */
    public Chapter(String name, String parentLegion) {
        this.name = name;
        this.parentLegion = parentLegion;
    }

    /**
     * Получить Chapter.name.
     *
     * @return Chapter.name
     */
    public String getName() {
        return name;
    }

    /**
     * Установить название Chapter.name.
     *
     * @param name название Chapter.name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить название parentLegion.
     *
     * @return название parentLegion
     */
    public String getParentLegion() {
        return parentLegion;
    }

    /**
     * Установить название parentLegion.
     *
     * @param parentLegion название parentLegion
     */
    public void setParentLegion(String parentLegion) {
        this.parentLegion = parentLegion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Chapter chapter = (Chapter) o;
        return Objects.equals(name, chapter.name)
                && Objects.equals(parentLegion, chapter.parentLegion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parentLegion);
    }

    @Override
    public String toString() {
        return "Chapter{"
                + "name='" + name + '\''
                + ", parentLegion: " + parentLegion
                + '}';
    }
}
