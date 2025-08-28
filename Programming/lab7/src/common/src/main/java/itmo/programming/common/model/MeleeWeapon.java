package itmo.programming.common.model;

import java.io.Serializable;

/**
 * Перечисление типов рукопашного оружия.
 */
public enum MeleeWeapon implements Serializable {
    POWER_SWORD,
    CHAIN_AXE,
    LIGHTING_CLAW,
    POWER_FIST;

    /**
     * Получить все доступные типы рукопашного оружия.
     *
     * @return массив всех типов оружия
     */
    public MeleeWeapon[] getMeleeWeapons() {
        return values();
    }
}
