package shorties;
import enums.Mood;

import java.util.Objects;



import exceptions.HungryException;

abstract public class Shorty {
    protected int id = 0;
    protected String name = "";
    protected boolean isHungry = false;
    protected boolean lookedRestaurant = false;
    protected boolean inRestaurant = false;
    protected Mood mood;

    public Shorty(boolean isHungry, boolean lookedRestaurant, boolean inRestaurant, Mood mood, int id){
        this.isHungry = isHungry;
        this.lookedRestaurant = lookedRestaurant;
        this.inRestaurant = inRestaurant;
        this.mood = mood;
        this.id = id;
    }

    public void walk() throws HungryException{

    };
    public void dance(){
        this.mood = Mood.HAPPY;
    }
    public int getId(){
        return this.id;
    }
    public Mood getMood(){
        return this.mood;
    }
    public boolean isInRestaurant(){
        return inRestaurant;
    }
    public void lookAtTheRestaurant(){
        this.lookedRestaurant = true;
    }
    public String getName(){
        return this.name;
    }


    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }


    @Override
    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Shorty shorty = (Shorty) obj;
        return (this.id == shorty.id);
    }
    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
