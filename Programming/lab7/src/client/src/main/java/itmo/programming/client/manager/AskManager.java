package itmo.programming.client.manager;

import itmo.programming.common.model.Chapter;
import itmo.programming.common.model.Coordinates;
import itmo.programming.common.model.MeleeWeapon;
import itmo.programming.common.model.SpaceMarine;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс, хранящий методы, запрашивающие значения полей SpaceMarine у клиента.
 */
public class AskManager {
    private static final int MIN_X_COORDINATE = -341;
    private static final int MIN_Y_COORDINATE = -91;
    private final BufferedReader reader;

    /**
     * Конструктор класса.
     *
     * @param reader reader.
     */
    public AskManager(BufferedReader reader) {
        this.reader = reader;
    }



    /**
     * Читает объект SpaceMarine из консоли.
     *
     * @return объект SpaceMarine
     * @throws IOException при ошибке чтения
     */
    public SpaceMarine askSpaceMarine() throws IOException {
        System.out.println("Введите SpaceMarine:");

        // Чтение имени
        String name;
        while (true) {
            System.out.print("name (Поле не может быть null, Строка не может быть пустой): ");
            name = reader.readLine().trim();
            if (!name.isEmpty()) {
                break;
            }
            System.out.println("Ошибка: поле не может быть пустым");
        }

        // Чтение координат
        final Coordinates coordinates = askCoordinates();

        // Чтение health
        double health;
        while (true) {
            System.out.print("Health (Значение поля должно быть больше 0): ");
            try {
                health = Double.parseDouble(reader.readLine().trim());
                if (health > 0) {
                    break;
                }
                System.out.println("Ошибка: значение должно быть больше 0");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число");
            }
        }

        // Чтение achievements
        String achievements = "";
        while (true) {
            System.out.print("achievements (Поле не может быть null): ");
            achievements = reader.readLine().trim();
            if (!achievements.isEmpty()) {
                break;
            }
            System.out.println("Ошибка: Поле не может быть null");
        }

        // Чтение height
        Float height;
        while (true) {
            System.out.print("height (Поле может быть null): ");
            try {
                height = Float.parseFloat(reader.readLine().trim());
                break;
            } catch (NumberFormatException e) {
                height = Float.parseFloat(String.valueOf(0));
                break;
            }
        }

        // Чтение оружия
        final MeleeWeapon meleeWeapon = askMeleeWeapon();

        // Чтение главы
        final Chapter chapter = askChapter();

        return new SpaceMarine(
                null,
                name,
                coordinates,
                LocalDateTime.now(),
                health,
                achievements,
                height,
                meleeWeapon,
                chapter
        );
    }

    /**
     * Читает координаты из консоли.
     *
     * @return объект Coordinates
     * @throws IOException при ошибке чтения
     */
    private Coordinates askCoordinates() throws IOException {
        System.out.println("Введите координаты:");

        // Чтение x
        Integer x;
        while (true) {
            System.out.print("x (Значение должно быть больше " + MIN_X_COORDINATE + "): ");
            try {
                x = Integer.parseInt(reader.readLine().trim());
                if (x > MIN_X_COORDINATE) {
                    break;
                }
                System.out.println("Ошибка: значение должно быть больше " + MIN_X_COORDINATE);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число");
            }
        }

        // Чтение y
        Integer y;
        while (true) {
            System.out.print("y (Значение должно быть больше " + MIN_Y_COORDINATE + "): ");
            try {
                y = Integer.parseInt(reader.readLine().trim());
                if (y > MIN_Y_COORDINATE) {
                    break;
                }
                System.out.println("Ошибка: значение должно быть больше " + MIN_Y_COORDINATE);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число");
            }
        }

        return new Coordinates(x, y);
    }

    /**
     * Читает тип оружия из консоли.
     *
     * @return объект MeleeWeapon
     * @throws IOException при ошибке чтения
     */
    private MeleeWeapon askMeleeWeapon() throws IOException {
        System.out.println("Выберите MeleeWeapon:");
        final MeleeWeapon[] weapons = MeleeWeapon.values();
        for (int i = 0; i < weapons.length; i++) {
            System.out.println((i + 1) + ". " + weapons[i]);
        }
        System.out.print("Введите номер либо же строку: ");
        while (true) {
            final String input = reader.readLine().trim();
            try {
                final int value = Integer.parseInt(input);
                final List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
                if (numbers.contains(value)) {
                    return MeleeWeapon.values()[value - 1];
                } else {
                    System.out.println("Нет такого номера!");
                }
            } catch (NumberFormatException e) {
                for (MeleeWeapon value : MeleeWeapon.values()) {
                    if (value.toString().equals(input.toUpperCase())) {
                        return value;
                    }
                }
                System.out.println("Нет такого значения MeleeWeapon!");
            }

        }
    }

    /**
     * Читает информацию об ордене из консоли.
     *
     * @return объект Chapter
     * @throws IOException при ошибке чтения
     */
    private Chapter askChapter() throws IOException {
        System.out.println("Введите Chapter:");

        String name;
        while (true) {
            System.out.print("name (не может быть пустым): ");
            name = reader.readLine().trim();
            if (!name.isEmpty()) {
                break;
            }
            System.out.println("Ошибка: поле не может быть пустым");
        }

        String parentLegion;
        while (true) {
            System.out.print("parentLegion (не может быть пустым): ");
            parentLegion = reader.readLine().trim();
            if (!parentLegion.isEmpty()) {
                break;
            }
            System.out.println("Ошибка: поле не может быть пустым");
        }

        return new Chapter(name, parentLegion);
    }
}
