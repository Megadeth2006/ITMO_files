package itmo.programming.common.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс координат, представляющий точку в двумерном пространстве.
 */
public class Coordinates implements Comparable<Coordinates>, Serializable {

    private Integer coordX; // Значение поля должно быть больше -341, Поле не может быть null
    private Integer coordY; // Значение поля должно быть больше -91, Поле не может быть null

    /**
     * Пустой конструктор для GSON.
     */
    public Coordinates() {
    }

    /**
     * Конструктор для создания координат.
     *
     * @param coordX координата X
     * @param coordY координата Y
     */
    public Coordinates(Integer coordX, Integer coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    /**
     * Вычисляет расстояние от начала координат до точки.
     *
     * @return расстояние от начала координат
     */
    public double distance() {
        return Math.sqrt(coordX * coordX + coordY * coordY);
    }

    /**
     * Получить координату X.
     *
     * @return координата X
     */
    public Integer getX() {
        return coordX;
    }

    /**
     * Установить координату X.
     *
     * @param coordX координата X
     */
    public void setX(Integer coordX) {
        this.coordX = coordX;
    }

    /**
     * Получить координату Y.
     *
     * @return координата Y
     */
    public Integer getY() {
        return coordY;
    }

    /**
     * Установить координату Y.
     *
     * @param coordY координата Y
     */
    public void setY(Integer coordY) {
        this.coordY = coordY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Coordinates that = (Coordinates) o;
        return Objects.equals(coordX, that.coordX)
                && Objects.equals(coordY, that.coordY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordX, coordY);
    }

    @Override
    public String toString() {
        return "Coordinates{"
                + "x=" + coordX
                + ", y=" + coordY
                + '}';
    }

    @Override
    public int compareTo(Coordinates o) {
        return Double.compare(distance(), o.distance());
    }
}
