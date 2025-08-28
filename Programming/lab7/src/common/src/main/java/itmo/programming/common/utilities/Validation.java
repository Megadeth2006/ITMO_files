package itmo.programming.common.utilities;

import itmo.programming.common.model.SpaceMarine;
import java.util.Arrays;

/**
 * Утилитарный класс для валидации объектов модели.
 */
public class Validation {
    private static final int X_CONST = -549;
    private static final int Y_CONST = -267;

    /**
     * Проверка на правильность полей SpaceMarine.
     *
     * @param object объект модели
     * @return true, если объект валиден
     */
    public static boolean isValidSpaceMarine(SpaceMarine object) {
        return object.getId() > 0 && object.getName() != null;
    }

    /**
     * Проверка на правильность полей Coordinates.
     *
     * @param object объект SpaceMarine
     * @return true, если координаты валидны
     */
    public static boolean isValidCoordinates(SpaceMarine object) {
        return object.getCoordinates().getX() > X_CONST 
                && object.getCoordinates().getY() > Y_CONST;
    }

    /**
     * Проверка на правильность полей Chapter.
     *
     * @param object объект SpaceMarine
     * @return true, если орден валиден
     */
    public static boolean isValidChapter(SpaceMarine object) {
        return object.getChapter().getName() != null 
                && !object.getChapter().getName().isEmpty();
    }

    /**
     * Проверка на правильность Enum MeleeWeapon.
     *
     * @param object объект SpaceMarine
     * @return true, если оружие валидно
     */
    public static boolean isValidEnum(SpaceMarine object) {
        return Arrays.asList(object.getMeleeWeapon().getMeleeWeapons())
                .contains(object.getMeleeWeapon());
    }
}
