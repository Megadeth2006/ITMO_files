package Moves.Status;
import ru.ifmo.se.pokemon.*;
// Agility raises the user's Speed by two stages.

// done
public class Agility extends StatusMove{
    public Agility(){
        super(Type.PSYCHIC, 0.0, 0.0);
    }
    @Override
    protected String describe() {
        return "делает " + getClass().getSimpleName() + "и повышает скорость на 2 ступени";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.SPEED, (int) (pokemon.getStat(Stat.SPEED)+2));
    }
}
