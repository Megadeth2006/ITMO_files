public class Badboy extends Man{
    String activity = "";
    public void SaySmth(String activity, String name, int age){
        this.activity = activity;
        System.out.println("Я теперь человек" + Integer.toString(age) + "с именем " + name);
        System.out.println(activity);

    }

    
}
