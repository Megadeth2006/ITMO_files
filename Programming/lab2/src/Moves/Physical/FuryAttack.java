package Moves.Physical;
import ru.ifmo.se.pokemon.*;

/*Fury Attack hits 2-5 times per turn used. The probability of each interval is shown in the table, with the total power after each hit.

   Each strike of Fury Attack is treated like a separate attack:

Each strike can get a critical hit independently.
Abilities that activate on contact (Static, Weak Armor, Iron Barbs etc.) activate for each strike.
If the Sturdy ability, Focus Sash, or Focus Band activates before the final hit, the subsequent hit will still deal damage and therefore cause the opponent to faint.
If the first hit breaks a Substitute, the second hit will deal damage to the target Pokémon.
The move Counter only counters the final hit, but Bide counters the complete damage.

The Skill Link ability will cause Fury Attack to always hit the maximum number of times.*/

public class FuryAttack extends PhysicalMove{
    public FuryAttack(){
        super(Type.NORMAL, 15.0, 85.0);

    }
    @Override
    protected String describe(){
        return "делает " + getClass().getSimpleName();
    }

        @Override
        protected void applyOppDamage(Pokemon pokemon, double v) {


            int chance = (int)(Math.random()/0.125);
            int cnt ;
            switch (chance){
                case 0, 1, 2 -> cnt = 2;
                case 3, 4, 5 -> cnt = 3;
                case 6 -> cnt = 4;
                case 7 -> cnt = 5;
                default -> throw new IllegalArgumentException("Unexpected value: " + chance);
            }

            for (int i = 1; i <= cnt; ++i){
                pokemon.setMod(Stat.HP, 15);
            }
        }
    }
