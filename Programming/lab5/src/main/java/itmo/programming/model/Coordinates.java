package itmo.programming.model;

/**
 * Класс Coordinates модели.
 */
public class Coordinates {
    private final int coordinatesX;
    private final double coordinateY;

    /**
     * Конструктор класса.
     *
     * @param coordinatesX x.
     *
     * @param coordinatesY y.
     */
    public Coordinates(int coordinatesX, double coordinatesY) {
        this.coordinatesX = coordinatesX;
        this.coordinateY = coordinatesY;
    }

    /**
     * Получить значение поля x.
     */
    public int getX() {
        return coordinatesX;
    }

    /**
     * Получить значение поля y.
     */
    public double getY() {
        return coordinateY;
    }

    @Override
    public String toString() {
        return "(x: " + coordinatesX + "; y: " + coordinateY + ")";
    }
}
