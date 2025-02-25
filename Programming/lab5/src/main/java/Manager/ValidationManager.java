package Manager;

import Model.MeleeWeapon;
import Model.SpaceMarine;
// TODO: нужно дописать код
public class ValidationManager {
    private ValidationManager(){}
    public static boolean isValidSpaceMarine(SpaceMarine object, CollectionManager collectionManager){
        return object.getId() > 0 && collectionManager.findById(object.getId()) == null && object.getName() != null;
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
