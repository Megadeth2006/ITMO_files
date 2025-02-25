package Model;

import java.util.ArrayList;
import java.util.Arrays;

public enum MeleeWeapon {
    POWER_SWORD,
    CHAIN_AXE,
    LIGHTING_CLAW,
    POWER_FIST;

    public static ArrayList<MeleeWeapon> names() {
        ArrayList<MeleeWeapon> weapons = new ArrayList<>();
        weapons.addAll(Arrays.asList(values()));
        return weapons;
    }
}
