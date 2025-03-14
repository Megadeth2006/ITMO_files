package enums;

public enum Mood {
    HAPPY("Счастливый"),
    ENVY("Завистливый"),
    SAD("Грустный");
    private final String h;
    Mood(String h){
        this.h = h;

    }
    public String getName(){
        return h;
    }
}
