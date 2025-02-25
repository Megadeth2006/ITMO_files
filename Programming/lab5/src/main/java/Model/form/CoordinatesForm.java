package Model.form;

import Manager.ConsoleManager;
import Model.Coordinates;

public class CoordinatesForm extends Form<Coordinates> {
    private final ConsoleManager console;

    public CoordinatesForm(ConsoleManager console) { // не понимаю, почему вызывается супер класс и this присваивается console
        super(console);
        this.console = console;
    }

    @Override
    public Coordinates build() {
        return new Coordinates(
                askInt("SpaceMarine.x", "(Значение поля должно быть больше -549)", x -> (x != null  && x > -549)),
                askDouble("SpaceMarine.y", "(Значение поля должно быть больше -267)", y -> (y != null && y > -267)));
    }
}
