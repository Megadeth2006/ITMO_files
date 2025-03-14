package environment;
import enums.StreetState;
import shorties.Shorty;

import java.util.ArrayList;

public class Street {
    protected String name;

    protected StreetState state;
    protected ArrayList<Shorty> shorties = new ArrayList<>();
    protected ArrayList<Advertisement> advertisementList = new ArrayList<>();

    public Street(String name){
        this.name = name;
    }

    public void addShorties(Shorty shorty){
        this.shorties.add(shorty);
    }
    public ArrayList<Shorty> getShorties(){
        return this.shorties;
    }
    public void addAD(Advertisement advertisement){
        this.advertisementList.add(advertisement);
    }
    public ArrayList<Advertisement> getAdvertisement(){
        return this.advertisementList;
    }
    public void removeAD(Advertisement advertisement){
        this.advertisementList.remove(advertisement);
    }
    public void setAD(ArrayList<Advertisement> advertisementList){
        this.advertisementList = advertisementList;
    }
    public void setState(StreetState state){
        this.state = state;
    }

    public String getName(){return this.name;}

    public StreetState getState(){
        return this.state;
    }

}
