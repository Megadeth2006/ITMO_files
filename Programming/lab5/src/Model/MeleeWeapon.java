package Model;

public enum MeleeWeapon{
    POWER_SWORD,
    CHAIN_AXE,
    LIGHTING_CLAW,
    POWER_FIST;
    public static String names(){
        StringBuilder listWeapons = new StringBuilder();
        for (var weaponType: values()){
            listWeapons.append(weaponType.name()).append(",");
        }
        return listWeapons.substring(0, listWeapons.length()-1);
    }
}
