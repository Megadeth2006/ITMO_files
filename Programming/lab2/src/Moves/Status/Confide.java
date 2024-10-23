package Moves.Status;
import ru.ifmo.se.pokemon.*;

//Confide lowers the target's Special Attack by one stage.
// done
public class Confide extends StatusMove{
    public Confide(){
        super(Type.NORMAL, 0.0, 0.0);
    }
    @Override
    protected String describe() {
        return "делает " + getClass().getSimpleName() + "и понижает специальную атаку на одну ступень";
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.SPECIAL_ATTACK, (int) (pokemon.getStat(Stat.SPECIAL_ATTACK)-1));
    }
}
