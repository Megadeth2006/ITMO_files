package environment;

import enums.Mood;

import java.util.ArrayList;

public class City {
    protected String name;
    protected ArrayList<Street> streets;

    public City(String name){
        this.name = name;
        this.streets = new ArrayList<Street>();
    }
    public City(String name, ArrayList<Street> streets){
        this.name = name;
        this.streets = streets;
    }
    public void addStreet(Street street){
        this.streets.add(street);
    }
    public void removeStreet(Street street){this.streets.remove(street);}
    public void setStreet(ArrayList<Street> streets){this.streets = streets;}
    public String getName(){
        return this.name;
    }
    public ArrayList<Street> getStreets(){
        return this.streets;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        City city = (City) obj;
        return (city.name == this.name && city.streets == this.streets);
    }
    @Override
    public String toString() {
        return "{name: " + this.name + ", streets: " + this.streets.toString() + "}";
    }



}
