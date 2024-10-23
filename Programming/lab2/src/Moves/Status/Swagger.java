package Moves.Status;
import ru.ifmo.se.pokemon.*;
//Swagger confuses the target and raises its Attack by two stages.
//done
public class Swagger extends StatusMove {
    public Swagger(){
        super(Type.NORMAL, 0.0, 85.0);
    }
    @Override
    protected String describe() {
        return "делает " + getClass().getSimpleName();
    }
    protected void applyOppEffects(Pokemon pokemon) {

        Effect.confuse(pokemon);
        pokemon.setMod(Stat.ATTACK, (int) (pokemon.getStat(Stat.ATTACK)+2));
    }
}
