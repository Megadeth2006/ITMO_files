package Moves.Physical;
import ru.ifmo.se.pokemon.*;

/*Spark deals damage and has a 30% chance of paralyzing the target.

Electric type Pokémon, those with the ability Limber or those behind a Substitute cannot be paralyzed.*/

// done
public class Spark extends PhysicalMove{
    public Spark(){
        super(Type.ELECTRIC, 65.0, 100.0);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if (0.3 >= Math.random()){
            Effect.paralyze(pokemon);
            System.out.println(pokemon.toString() + " производит атаку Spark и парализует цель");
        }
        System.out.println(pokemon.toString() + " производит атаку Spark");
    }
}
