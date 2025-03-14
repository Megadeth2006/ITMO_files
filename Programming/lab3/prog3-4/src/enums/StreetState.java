package enums;

public enum StreetState {
    HAPPINESS("Радость"),
    MUSICALITY("Музыкой"),
    SHINE("Сиянияем"),
    ELECTRICAL_LIGHTNING("Электрическим свечением"),
    NOISY("Шумом");
    private final String name;
    StreetState(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
