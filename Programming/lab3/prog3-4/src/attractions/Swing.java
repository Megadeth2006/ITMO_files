package attractions;

import enums.AttractionStatus;
import enums.Mood;
import shorties.OtherShorty;
import shorties.Shorty;

import java.util.ArrayList;
import java.util.Objects;

public class Swing extends Attraction {
    protected Shorty user =  new OtherShorty(false, false, false, Mood.SAD, 0);;
    public Swing(AttractionStatus status){
        super(status);
        this.name = "Качель";
        if (status == AttractionStatus.UNDER_REPAIR){
            System.out.println("Аттракцион " + this.name + " сломан");
        }
    }


    public void loadUser(Shorty user){
        if (isAvailable()){
            this.user = user;
            System.out.println("Коротышка " + user.getId() + " решил воспользоваться скрипящими качелями");
        }else{
            System.out.println("Сейчас невозможно добавить пользователя на аттракцион " + this.name);
        }
    }
    @Override
    public void start()  {
        if (this.status == AttractionStatus.UNDER_REPAIR || Objects.equals(this.user.getId(), 0)) {
            System.out.println("Аттракцион " + this.name + " не может работать сейчас: сломан или никто не сидит");
        }else{
            this.status = AttractionStatus.OPERATIONAL;
            System.out.println("Аттракцион " + this.name + " запущен в работу!");
        }
    }
    @Override
    public void stop(){
        this.status = AttractionStatus.NOT_RUNNING;
        System.out.println("Аттракцион " + this.name + " перестал качаться! Подошло время покинуть аттракцион");
    }
    @Override
    public boolean isAvailable(){
        if (this.status == AttractionStatus.OPERATIONAL || this.status == AttractionStatus.UNDER_REPAIR){
            return false;
        }
        else return true;
    }


}
