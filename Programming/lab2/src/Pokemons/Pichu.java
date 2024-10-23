package Pokemons;
import Moves.Physical.Facade;
import Moves.Physical.FlareBlitz;
import Moves.Physical.Spark;
import Moves.Special.Thunder;
import Moves.Status.Confide;
import Moves.Status.Swagger;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;


public class Pichu extends Pokemon{
    public Pichu(){
        super("Мелкий покемон", 0 );
        setStats(20, 40, 15, 35, 35, 60);
        setType(Type.ELECTRIC);
        setMove(new Confide(), new Swagger(), new Spark());
    }
}
