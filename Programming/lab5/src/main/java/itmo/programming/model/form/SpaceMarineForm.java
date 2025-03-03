package itmo.programming.model.form;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.manager.IdManager;
import itmo.programming.model.Chapter;
import itmo.programming.model.Coordinates;
import itmo.programming.model.MeleeWeapon;
import itmo.programming.model.SpaceMarine;
import java.time.LocalDateTime;

public class SpaceMarineForm extends Form<SpaceMarine> {
    private final ConsoleManager console;

    public SpaceMarineForm(ConsoleManager console) {
        super(console);
        this.console = console;
    }

    @Override
    public SpaceMarine build() {
        return new SpaceMarine(IdManager.generateId(),
                askString("SpaceMarine.name", "(Поле не может быть null, Строка не может быть пустой)",
                        s -> !s.isEmpty()),
                askCoordinates(),
                LocalDateTime.now(),
                askLong("SpaceMarine.health", "(Значение поля должно быть больше 0)",
                        h -> (h != null && h > 0)),
                askString("SpaceMarine.achievements", "(Поле может быть null)",
                        h -> true),
                askInt("SpaceMarine.height", "", h -> true),
                askMeleeWeapon(),
                askChapter()
        );
    }

    public SpaceMarine updater(int id) {
        return new SpaceMarine(id,
                askString("SpaceMarine.name", "(Поле не может быть null, Строка не может быть пустой)",
                        s -> !s.isEmpty()),
                askCoordinates(),
                LocalDateTime.now(),
                askLong("SpaceMarine.health", "(Значение поля должно быть больше 0)",
                        h -> (h != null && h > 0)),
                askString("SpaceMarine.achievements", "(Поле может быть null)", h -> true),
                askInt("SpaceMarine.height", "", h -> true),
                askMeleeWeapon(),
                askChapter());
    }

    private Chapter askChapter() {
        return new ChapterForm(console).build();
    }

    private MeleeWeapon askMeleeWeapon() {
        return askEnum("MeleeWeapon", s -> true);
    }

    private Coordinates askCoordinates() {
        return new CoordinatesForm(console).build();
    }


}
