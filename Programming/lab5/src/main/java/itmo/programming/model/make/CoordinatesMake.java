package itmo.programming.model.make;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.Coordinates;

/**
 * Класс для запрашивания и заполнения полей Coordinates.
 */
public class CoordinatesMake extends Make<Coordinates> {
    static final int xConstant = -549;
    static final int yConstant = -267;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     */

    public CoordinatesMake(ConsoleManager console) {
        super(console);
    }

    @Override
    public Coordinates build() {
        return new Coordinates(
                askInt("SpaceMarine.x", "(int, Значение поля должно быть больше -549)",
                        x -> (x != null && x > xConstant)),
                askDouble("SpaceMarine.y", "(double, Значение поля должно быть больше -267)",
                        y -> (y != null && y > yConstant)));
    }
}
