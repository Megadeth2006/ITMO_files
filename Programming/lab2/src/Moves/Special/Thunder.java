package Moves.Special;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;

// Thunder deals damage and has a 30% chance of paralyzing the target.
public class Thunder extends SpecialMove{
    public Thunder(){
        super(Type.ELECTRIC, 110, 70);

    }
    @Override
    protected String describe() {
        return  "делает " + getClass().getSimpleName() + " и имеет 30% шанс парализовать цель";
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if (0.3 >= Math.random()){
            Effect.paralyze(pokemon);
        }
    }
}
