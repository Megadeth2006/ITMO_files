package Pokemons;
import Moves.Physical.*;
import Moves.Status.*;
import Moves.Special.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Raichu extends Pokemon{
    public Raichu(String name, int level){
        super(name, level);
        setStats(60, 90, 55, 90, 80, 110);
        setType(Type.ELECTRIC);
        setMove(new Confide(), new Swagger(), new Spark(), new TailWhip());
    }
}
