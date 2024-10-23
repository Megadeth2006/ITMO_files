package Pokemons;
import Moves.Physical.Facade;
import Moves.Physical.FlareBlitz;
import Moves.Special.Thunder;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Pikachu extends Pokemon{
    public Pikachu(){
        super("Топ чел", 0 );
        setStats(35, 55, 40, 50, 50, 90);
        setType(Type.ELECTRIC);
        setMove(new Facade(), new FlareBlitz(), new Thunder());
    }
}
