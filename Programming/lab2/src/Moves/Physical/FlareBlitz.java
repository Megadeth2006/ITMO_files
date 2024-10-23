package Moves.Physical;
import ru.ifmo.se.pokemon.*;

/* Flare Blitz deals damage and has a 10% chance of burning the target, but the user receives 1⁄3 of the damage it inflicted in recoil. In other words, if the attack does 90 HP damage to the opponent, the user will lose 30 HP.

Fire type Pokémon, those with the ability Water Veil or those behind a Substitute cannot be burned.

If the user is frozen, Flare Blitz can be used and will thaw out the Pokémon before attacking.*/

// done
public class FlareBlitz extends PhysicalMove{
    public FlareBlitz(){
        super(Type.FIRE, 120.0, 100.0);
    }


    @Override
    protected double calcBaseDamage(Pokemon att, Pokemon def) {
        if (0.1 >= Math.random()){
            Effect.burn(att);
            applySelfDamage(att, (double)1/3*calcBaseDamage(att, def));
            System.out.print(att.toString() + " наносит атаку Flareblitz и испепеляет врага, теряя ХП = 1/3 power от своей атаки");
        }


        return super.calcBaseDamage(att, def);
    }
}
