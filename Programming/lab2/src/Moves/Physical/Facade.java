package Moves.Physical;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.*;
/* Facade deals damage, and hits with double power (140) if the user is burned, poisoned or paralyzed.

In the case of a burn, the usual attack-halving still occurs so Facade hits with an effective power of 70.*/

// done
public class Facade extends PhysicalMove {
    public Facade(){
        super(Type.NORMAL, 70.0, 100.0);

    }


    @Override
    protected double calcBaseDamage(Pokemon att, Pokemon def) { // done
        Status Condition = att.getCondition();
        if (Condition == Status.BURN ||  Condition == Status.POISON || Condition == Status.PARALYZE){
            System.out.println(att.toString() + " наносит атаку Facade с удвоенной силой");
            return 2*super.calcBaseDamage(att, def);

        }
        System.out.println(att.toString() + " наносит атаку Facade");
        return super.calcBaseDamage(att, def);
    }
}
