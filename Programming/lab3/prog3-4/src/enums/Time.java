package enums;

public enum Time {
    MORNING("Утро"),
    DAY("День"),
    EVENING("Вечер"),
    NIGHT("Ночь");
    private final String name;
    Time(String name){
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
