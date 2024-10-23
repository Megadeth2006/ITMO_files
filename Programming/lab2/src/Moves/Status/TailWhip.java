package Moves.Status;
import ru.ifmo.se.pokemon.*;

// Tail Whip lowers the target's Defense by one stage.

// done
public class TailWhip extends StatusMove{
    public TailWhip(){
        super(Type.NORMAL, 0.0, 100.0);
    }

    @Override
    protected String describe() {
        return "делает " + getClass().getSimpleName() + " и понижает защиту на 1 ступень";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.DEFENSE, (int)pokemon.getStat(Stat.DEFENSE)-1);
    }
}
