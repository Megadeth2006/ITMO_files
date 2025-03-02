package Manager;

import Model.MeleeWeapon;
import Model.SpaceMarine;

public class ValidationManager {
    private ValidationManager(){}
    public static boolean isValidSpaceMarine(SpaceMarine object, CollectionManager collection){
        return object.getId() > 0 && collection.findById(object.getId()) == null && object.getName() != null;
    }

    public static boolean isValidCoordinates(SpaceMarine object){
        return object.getX() > -549 && object.getY() > -267;
    }
    public static boolean isValidChapter(SpaceMarine object){
        return object.getChapterName() != null && !object.getChapterName().isEmpty();
    }
    public static boolean isValidEnum(SpaceMarine object){
        return MeleeWeapon.names().contains(object.getMeleeWeapon());
    }
}
