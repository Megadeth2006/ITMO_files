package attractions;

import enums.AttractionStatus;
import enums.Mood;
import shorties.OtherShorty;
import shorties.Shorty;

import java.util.ArrayList;
import java.util.Objects;

public class FerrisWheel extends Attraction {
    protected ArrayList<Shorty> users = new ArrayList<>();
    protected Shorty launcher =  new OtherShorty(false, false, false, Mood.SAD, 0);;

    public FerrisWheel(AttractionStatus status){
        super(status);
        this.name = "Чертово колесо";
        if (status == AttractionStatus.UNDER_REPAIR){
            System.out.println("Аттракцион " + this.name + " сломан");
        }

    }

    public void setLauncher(Shorty launcher){
        this.launcher = launcher;
        System.out.println("Коротышка " + launcher.getId() + " стал контроллером " + this.name);
    }
    public void loadUsers(Shorty user) {
        this.users.add(user);
    }

    @Override
    public void start()  {
        if (this.status == AttractionStatus.UNDER_REPAIR || Objects.equals(launcher.getId(), 0)) {
            System.out.println("Аттракцион " + this.name + " нельзя запустить в данный момент: не работает или нет контроллера");
        }else{
            if (this.users.isEmpty()){
                System.out.println("Никто не сидит на аттракционе " + this.name);
            }else{
                this.status = AttractionStatus.OPERATIONAL;
                System.out.println("Аттракцион " + this.name + " запущен в работу!");
            }
        }
    }
    @Override
    public boolean isAvailable(){
        if (this.status == AttractionStatus.OPERATIONAL || this.status == AttractionStatus.UNDER_REPAIR){
            return false;
        }
        else return true;
    }



}
