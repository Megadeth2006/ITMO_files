package Moves.Status;

import ru.ifmo.se.pokemon.*;
// Double Team raises the user's Evasiveness by one stage, thus making the user more difficult to hit.
// done
public class DoubleTeam extends StatusMove  {
    public DoubleTeam() {
        super(Type.NORMAL, 0.0, 0.0);
    }
    @Override
    protected String describe() {
        return "делает " + getClass().getSimpleName() + " и понижает уклончивость пользователя на одну ступень";
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.EVASION, (int)pokemon.getStat(Stat.EVASION)-1);
    }
}
