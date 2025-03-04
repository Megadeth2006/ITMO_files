package itmo.programming.manager;

import itmo.programming.model.MeleeWeapon;
import itmo.programming.model.SpaceMarine;

/**
 * Класс для проверки объектов модели, лежащих в файле.
 */
public class ValidationManager {
    static final int xConst = -549;
    static final int yConst = -267;

    /**
     * Проверка на правильность полей SpaceMarine.
     *
     * @param object объект модели.
     *
     * @param collection объект менеджера коллекции.
     */

    public static boolean isValidSpaceMarine(SpaceMarine object, CollectionManager collection) {
        return object.getId() > 0 && collection.findById(
                object.getId()) == null && object.getName() != null;
    }

    /**
     * Проверка на правильность полей Coordinates.
     *
     * @param object объект SpaceMarine.
     */

    public static boolean isValidCoordinates(SpaceMarine object) {
        return object.getX() > xConst && object.getY() > yConst;
    }

    /**
     * Проверка на правильность полей Chapter.
     *
     * @param object объект SpaceMarine.
     */

    public static boolean isValidChapter(SpaceMarine object) {
        return object.getChapterName() != null && !object.getChapterName().isEmpty();
    }

    /**
     * Проверка на правильность Enum MeleeWeapon.
     *
     * @param object объект SpaceMarine.
     */
    public static boolean isValidEnum(SpaceMarine object) {
        return MeleeWeapon.names().contains(object.getMeleeWeapon());
    }
}
