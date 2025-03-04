package itmo.programming.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Enum MeleeWeapon модели.
 */
public enum MeleeWeapon {
    POWER_SWORD,
    CHAIN_AXE,
    LIGHTING_CLAW,
    POWER_FIST;

    /**
     * Получить все именные константы.
     */
    public static ArrayList<MeleeWeapon> names() {

        final ArrayList<MeleeWeapon> meleeWeapons = new ArrayList<>();
        Collections.addAll(meleeWeapons, MeleeWeapon.values());
        return meleeWeapons;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
