package itmo.programming.model.make;

import itmo.programming.manager.ConsoleManager;
import itmo.programming.manager.ScannerManager;
import itmo.programming.model.MeleeWeapon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Абстрактный класс для запрашивания данных разных типов (Как примитивные, так и ссылочные).
 *
 * @param <T> generic.
 */
public abstract class Make<T> {
    private ConsoleManager console = null;
    private final Scanner scanner = ScannerManager.getScanner();

    /**
     * Конструктор абстрактного класса.
     *
     * @param console объект менеджера консоли.
     */
    public Make(ConsoleManager console) {
        this.console = console;
    }

    /**
     * Метод для заполнения полей, который реализует наследник.
     */
    public abstract T build();

    /**
     * Метод для запрашивания и получения строки.
     *
     * @param fieldName имя поля.
     *
     * @param restrictions ограничения.
     *
     * @param validator проверка.
     */
    public String askString(String fieldName, String restrictions, Predicate<String> validator) {
        while (true) {
            console.print("Введите " + fieldName + " " + restrictions + ": ");
            final String input = scanner.nextLine().trim();
            if (validator.test(input)) {
                return input;
            } else {
                if (input.isEmpty() && validator.test("")) {
                    return null;
                } else {
                    console.printErr("Неправильный формат ввода");
                }
            }
        }

    }

    /**
     * Метод для запрашивания и получения enum.
     *
     * @param field имя поля.
     *
     * @param validator проверка.
     */
    public MeleeWeapon askEnum(String field, Predicate<String> validator) {
        int c = 1;
        while (true) {
            console.print("Введите " + field + " (Заглавными,"
                    + " строчными буквами, либо же введите номер):"
                    + "\n" + "Доступные значения: \n");
            for (MeleeWeapon value : MeleeWeapon.values()) {
                console.println(c++ + ". " + value.toString());
            }
            c = 1;
            final String input = scanner.nextLine().trim();
            try {
                final int value = Integer.parseInt(input);
                final List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
                if (numbers.contains(value)) {
                    return MeleeWeapon.values()[value - 1];
                } else {
                    console.printErr("Нет такого номера!");
                }
            } catch (NumberFormatException e) {
                if (validator.test(input)) {
                    for (MeleeWeapon value : MeleeWeapon.values()) {
                        if (value.toString().equals(input.toUpperCase())) {
                            return value;
                        }
                    }
                    console.printErr("Значение не найдено");
                } else {
                    console.printErr("Неправильный формат ввода");
                }
            } catch (NoSuchElementException e) {
                console.printErr("Введено некорректное значение");
            }

        }
    }

    /**
     * Метод для запрашивания и получения объекта типа Integer.
     *
     * @param field имя поля.
     *
     * @param restrictions ограничения.
     *
     * @param validator проверка.
     */
    public Integer askInt(String field, String restrictions, Predicate<Integer> validator) {
        while (true) {
            console.print("Введите" + field + " " + restrictions + ": ");
            final String input = scanner.nextLine().trim();
            try {
                final Integer number = Integer.parseInt(input);
                if (validator.test(number)) {
                    return number;
                } else {
                    console.printErr("Ошибка проверки");
                }
            } catch (NumberFormatException e) {

                if (input.isEmpty() && validator.test(null)) {

                    return 0;
                } else {
                    console.printErr("Неверный формат ввода");
                }
            }
        }
    }

    /**
     * Метод для запрашивания и получения объекта типа Long.
     *
     * @param field имя поля.
     *
     * @param restrictions ограничения.
     *
     * @param validator проверка.
     */
    public Long askLong(String field, String restrictions, Predicate<Long> validator) {
        while (true) {
            console.print("Введите " + field + " " + restrictions + ": ");
            final String input = scanner.nextLine().trim();
            try {
                final Long number = Long.parseLong(input);
                if (validator.test(number)) {
                    return number;
                } else {
                    console.printErr("Ошибка проверки");
                }
            } catch (NumberFormatException e) {
                if (input.isEmpty() && validator.test(null)) {
                    return null;
                } else {
                    console.printErr("Неверный формат ввода");
                }
            }
        }
    }

    /**
     * Метод для запрашивания и получения объекта типа Double.
     *
     * @param fieldName имя поля.
     *
     * @param restrictions ограничения.
     *
     * @param validator проверка.
     */
    public Double askDouble(String fieldName, String restrictions, Predicate<Double> validator) {
        while (true) {
            console.print("Введите " + fieldName + " " + restrictions + ": ");
            final String input = scanner.nextLine().trim().replace(",", ".");
            try {
                final Double number = Double.parseDouble(input);
                if (validator.test(number)) {
                    return number;
                } else {
                    console.printErr("Ошибка валидации");
                }
            } catch (NumberFormatException e) {
                if (input.isEmpty() && validator.test(null)) {
                    return null;
                }
                console.printErr("Неверный формат ввода");
            }
        }
    }

}
