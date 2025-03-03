package itmo.programming.model;

public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String parentLegion;

    public Chapter() {
    }

    public Chapter(String name, String parentLegion) {
        this.name = name;
        this.parentLegion = parentLegion;
    }

    public String getChapterName() {
        return name;
    }

    public String getParentLegion() {
        return parentLegion;
    }

    @Override
    public String toString() {
        return "(name: " + name + "; parentLegion: " + parentLegion + ")";
    }
}
