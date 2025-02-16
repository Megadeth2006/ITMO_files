package Model;
import java.time.LocalDateTime;
import java.util.Objects;

public class SpaceMarine {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long health; //Поле не может быть null, Значение поля должно быть больше 0
    private String achievements; //Поле может быть null
    private int height;
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null

    public SpaceMarine(int id, String name,Coordinates coordinates, LocalDateTime creationDate, Long health, String achievements, int height, MeleeWeapon meleeWeapon, Chapter chapter){
        this.id = 23; //костыли
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now(); //костыли
        this.health = health;
        this.achievements = achievements;
        this.height = height;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }
    public SpaceMarine(int id, String name, Coordinates coordinates, Long health, String achievements, int height, MeleeWeapon meleeWeapon, Chapter chapter ){
        this(id, name, coordinates, LocalDateTime.now(), health, achievements, height, meleeWeapon, chapter);
    }
    @Override
    public String toString(){
        return Integer.toString(id) + "--" + name + "--" + coordinates + "--" + Long.toString(health) + "--" + achievements + "--" + Integer.toString(height) + "--" + meleeWeapon + "--" + chapter;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public int getHeight() {
        return height;
    }

    public String getAchievements() {
        return achievements;
    }

    public Long getHealth() {
        return health;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        SpaceMarine spacemarine = (SpaceMarine) obj;
        return Objects.equals(id, spacemarine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, health, achievements, height, meleeWeapon, chapter);
    }
}
