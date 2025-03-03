package itmo.programming.model.form;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.model.Coordinates;

public class CoordinatesForm extends Form<Coordinates> {
    private final ConsoleManager console;
    static final int xConstant = -549;
    static final int yConstant = -267;

    public CoordinatesForm(ConsoleManager console) { // не понимаю, почему вызывается супер класс и this присваивается console
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
