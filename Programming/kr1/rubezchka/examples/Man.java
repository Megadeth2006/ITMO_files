public class Man {
    int age = 0;
    String name = "";
    public void initialize(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Я человек " + Integer.toString(age) + " с именем " + name);
    }

}
