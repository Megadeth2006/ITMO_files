package itmo.programming.model.make;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.manager.IdManager;
import itmo.programming.model.Chapter;
import itmo.programming.model.Coordinates;
import itmo.programming.model.MeleeWeapon;
import itmo.programming.model.SpaceMarine;
import java.time.LocalDateTime;

/**
 * Класс для запрашивания, заполнения полей SpaceMarine и последующей сборки экземпляра модели.
 */
public class SpaceMarineMake extends Make<SpaceMarine> {
    private final ConsoleManager console;

    /**
     * Конструктор класса.
     *
     * @param console объект менеджера консоли.
     */
    public SpaceMarineMake(ConsoleManager console) {
        super(console);
        this.console = console;
    }

    /**
     * Запрашивание полей и возврат экземпляра собранной модели.
     */
    @Override
    public SpaceMarine build() {
        return new SpaceMarine(IdManager.generateId(),
                askString("SpaceMarine.name",
                        "(String, Поле не может быть null, Строка не может быть пустой)",
                        s -> !s.isEmpty()),
                askCoordinates(),
                LocalDateTime.now(),
                askLong("SpaceMarine.health", "(Long, Значение поля должно быть больше 0)",
                        h -> (h != null && h > 0)),
                askString("SpaceMarine.achievements", "(String, Поле может быть null)",
                        h -> true),
                askInt("SpaceMarine.height", "(int)", h -> true),
                askMeleeWeapon(),
                askChapter()
        );
    }

    /**
     * Метод для обновления существующего в коллекции экземпляра модели.
     *
     * @param id id.
     */
    public SpaceMarine update(int id) {
        return new SpaceMarine(id,
                askString("SpaceMarine.name",
                        "(String, Поле не может быть null, Строка не может быть пустой)",
                        s -> !s.isEmpty()),
                askCoordinates(),
                LocalDateTime.now(),
                askLong("SpaceMarine.health", "(Long, Значение поля должно быть больше 0)",
                        h -> (h != null && h > 0)),
                askString("SpaceMarine.achievements", "(String, Поле может быть null)", h -> true),
                askInt("SpaceMarine.height", "(int)", h -> true),
                askMeleeWeapon(),
                askChapter());
    }

    /**
     * Запрашивание полей Chapter.
     */
    private Chapter askChapter() {
        return new ChapterMake(console).build();
    }

    /**
     * Запрашивание полей MeleeWeapon.
     */
    private MeleeWeapon askMeleeWeapon() {
        return askEnum("MeleeWeapon", s -> true);
    }

    /**
     * Запрашивание полей Coordinates.
     */
    private Coordinates askCoordinates() {
        return new CoordinatesMake(console).build();
    }


}
