package shorties;

import enums.Mood;
import exceptions.HungryException;

public class Neznaika extends Shorty {
    public Neznaika(boolean isHungry, boolean lookedRestaurant, boolean inRestaurant, Mood mood, int id){
        super(isHungry, lookedRestaurant, inRestaurant, mood, id);
        this.name = "Незнайка";
    }
    public boolean getLookedRestaurant(){
        return this.lookedRestaurant;
    }
    @Override
    public void walk() throws HungryException {
        if (this.isHungry && this.lookedRestaurant && this.mood == Mood.SAD) throw new HungryException("Коротышка голоден, значит пойти на прогулку не может");
        System.out.println(this.name + " гуляет с настроением " + this.mood.getName());
    }

    @Override
    public void lookAtTheRestaurant(){
        this.lookedRestaurant = true;
        System.out.println(this.name + " посмотрел на ресторан. О, нет!");
    }
}
