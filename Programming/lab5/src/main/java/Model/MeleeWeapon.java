package Model;

import java.util.ArrayList;
import java.util.Arrays;

public enum MeleeWeapon {
    POWER_SWORD,
    CHAIN_AXE,
    LIGHTING_CLAW,
    POWER_FIST;

    public static ArrayList<MeleeWeapon> names() {

        ArrayList<MeleeWeapon> meleeWeapons = new ArrayList<>();
        for (MeleeWeapon value: MeleeWeapon.values()){
            meleeWeapons.add(value);
        }
        return meleeWeapons;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
