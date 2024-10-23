package Moves.Physical;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Move;
import ru.ifmo.se.pokemon.DamageMove;

public class DrillRun extends PhysicalMove{

    /* Drill Run deals damage and has an increased critical hit ratio (1⁄8 instead of 1⁄24).*/

    // done
    public DrillRun(){
        super(Type.GROUND, 80.0, 95.0);
    }

    @Override
    protected double calcCriticalHit(Pokemon pokemon, Pokemon pokemon1) {

        return (float)1/8*super.calcCriticalHit(pokemon, pokemon1);
    }

    @Override
    protected String describe() {
        return "наносит урон с повышенным коэффициентом критического удара " + getClass().getSimpleName();
    }
}
