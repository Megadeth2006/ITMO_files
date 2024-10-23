package Moves.Status;

import ru.ifmo.se.pokemon.*;

// Will-O-Wisp causes the target to become burned, if it hits. Burned Pokémon lose 1⁄8 of their maximum HP each turn and their Attack is decreased by 50%.
// done
public class WillOWisp extends StatusMove{
    public WillOWisp(){
        super(Type.FIRE, 0, 85);

    }

    @Override
    protected String describe() {
        return "делает " + getClass().getSimpleName() + " и обжигает цель";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect.burn(pokemon);
        pokemon.setMod(Stat.HP, (int)(pokemon.getStat(Stat.HP)*7/8));
        pokemon.setMod(Stat.ATTACK, (int)(pokemon.getStat(Stat.ATTACK)*1/2));
    }

}
