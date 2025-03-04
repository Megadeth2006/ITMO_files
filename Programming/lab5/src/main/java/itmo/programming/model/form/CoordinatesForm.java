package itmo.programming.model.form;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.Coordinates;

/**
 * Класс для запрашивания и заполнения полей Coordinates.
 */
public class CoordinatesForm extends Form<Coordinates> {
    static final int xConstant = -549;
    static final int yConstant = -267;
    private final ConsoleManager console;
    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     */

    public CoordinatesForm(ConsoleManager console) {
        super(console);
        this.console = console;
    }

    @Override
    public Coordinates build() {
        return new Coordinates(
                askInt("SpaceMarine.x", "(Значение поля должно быть больше -549)",
                        x -> (x != null && x > xConstant)),
                askDouble("SpaceMarine.y", "(Значение поля должно быть больше -267)",
                        y -> (y != null && y > yConstant)));
    }
}
