package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
// TODO: разобраться с полями
public class SpaceMarine implements Comparable<SpaceMarine>{
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long health; //Поле не может быть null, Значение поля должно быть больше 0
    private String achievements; //Поле может быть null
    private int height;
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null

    public SpaceMarine(int id, String name, Coordinates coordinates, LocalDateTime creationDate, Long health, String achievements, int height, MeleeWeapon meleeWeapon, Chapter chapter){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.achievements = achievements;
        this.height = height;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }
    public String getName(){
        return name;
    }
    public int getX(){
        return coordinates.getX();
    }
    public double getY(){
        return coordinates.getY();
    }
    public Long getHealth(){
        return health;
    }
    public String getAchievements(){
        return achievements;
    }
    public int getHeight(){
        return height;
    }
    public String getChapterName(){
        return chapter.getChapterName();
    }
    public MeleeWeapon getMeleeWeapon(){
        return meleeWeapon;
    }
    public static ArrayList<MeleeWeapon> getAllWeapons(){
        return MeleeWeapon.names();
    }
    public String getParentLegion(){
        return chapter.getParentLegion();
    }
    @Override
    public String toString() {
        return "SpaceMarine: (id: " + Integer.toString(id) +
                " | name: " + name +
                " | coordinates: " + coordinates +
                " | creationDate: " + creationDate +
                " | health: " + Long.toString(health) +
                " | achievements: " + achievements +
                " | height: " + Integer.toString(height) +
                " | meleeWeapon: " + meleeWeapon +
                " | chapter: " + chapter + ")";
    }


    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public int getId(){
        return id;
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

    @Override
    public int compareTo(SpaceMarine object) { //спросить про сортировку по умолчанию
        return Comparator.comparingInt(SpaceMarine::getId).compare(this, object);
    }

}
