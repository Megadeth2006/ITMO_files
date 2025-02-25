package Model.form;

import Manager.IdManager;
import Model.Chapter;
import Model.Coordinates;
import Model.MeleeWeapon;
import Model.SpaceMarine;
import Manager.ConsoleManager;
import javax.xml.crypto.Data;

import java.util.Date;
import java.time.LocalDateTime;
public class SpaceMarineForm extends Form<SpaceMarine> {
    private final ConsoleManager console;
    public SpaceMarineForm(ConsoleManager console){
        super(console);
        this.console = console;
    }

    @Override
    public SpaceMarine build() {
        return new SpaceMarine(IdManager.generateId(),
                askString("SpaceMarine.name", "(Поле не может быть null, Строка не может быть пустой)", s -> !s.isEmpty()),
                askCoordinates(),
                LocalDateTime.now(),
                askLong("SpaceMarine.health", "(Значение поля должно быть больше 0)", h -> (h != null && h > 0)),
                askString("SpaceMarine.achievements", "(Поле может быть null)", h -> true),
                askInt("SpaceMarine.height", "", h -> true),
                askMeleeWeapon(),
                askChapter()
                );
    }

    private Chapter askChapter() {
        return new ChapterForm(console).build();
    }

    private MeleeWeapon askMeleeWeapon() {
        return (MeleeWeapon) askEnum("MeleeWeapon", MeleeWeapon.values(), s -> true);
    }

    private Coordinates askCoordinates() {
        return new CoordinatesForm(console).build();
    }


}
