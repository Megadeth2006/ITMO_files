package Pokemons;
import Moves.Physical.Facade;
import Moves.Physical.FlareBlitz;
import Moves.Physical.FuryAttack;
import Moves.Special.Thunder;
import Moves.Status.Agility;
import Moves.Status.DoubleTeam;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Spearow extends Pokemon{
    public Spearow(){
        super("Мелкая птица", 0 );
        setStats(40, 60, 30, 31, 31, 70);
        setType(Type.NORMAL, Type.FLYING);
        setMove(new DoubleTeam(), new FuryAttack(), new Agility());
    }
}
