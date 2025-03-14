package attractions;

import enums.AttractionStatus;

public abstract class Attraction implements AttractionUsing {
    protected String name;
    protected AttractionStatus status;


    public Attraction(AttractionStatus status){

        this.status = status;
    }
    public AttractionStatus getStatus(){
        return this.status;
    }


    abstract public void start();
    public void stop(){

        this.status = AttractionStatus.NOT_RUNNING;

    }


    abstract boolean isAvailable(); {}

    @Override
    public String toString(){return "{name: " + this.name + "}";}
}