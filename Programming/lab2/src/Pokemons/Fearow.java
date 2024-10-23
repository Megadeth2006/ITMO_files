package Pokemons;
import Moves.Physical.DrillRun;
import Moves.Physical.Facade;
import Moves.Physical.FlareBlitz;
import Moves.Physical.FuryAttack;
import Moves.Special.Thunder;
import Moves.Status.Agility;
import Moves.Status.DoubleTeam;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;


public class Fearow extends Pokemon{
    public Fearow(){
        super("Коричневый гусь", 0);
        setStats(65, 90, 65, 61, 61, 100);
        setType(Type.NORMAL, Type.FLYING);
        setMove(new DoubleTeam(), new FuryAttack(), new Agility(), new DrillRun());
    }
}
