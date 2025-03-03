package itmo.programming.manager;

import itmo.programming.model.MeleeWeapon;
import itmo.programming.model.SpaceMarine;

public class ValidationManager {
    static final int xConst = -549;
    static final int yConst = -267;

    private ValidationManager() {
    }

    public static boolean isValidSpaceMarine(SpaceMarine object, CollectionManager collection) {
        return object.getId() > 0 && collection.findById(object.getId()) == null && object.getName() != null;
    }

    public static boolean isValidCoordinates(SpaceMarine object) {
        return object.getX() > xConst && object.getY() > yConst;
    }

    public static boolean isValidChapter(SpaceMarine object) {
        return object.getChapterName() != null && !object.getChapterName().isEmpty();
    }

    public static boolean isValidEnum(SpaceMarine object) {
        return MeleeWeapon.names().contains(object.getMeleeWeapon());
    }
}
