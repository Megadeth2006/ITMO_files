package Pokemons;
import Moves.Physical.Facade;
import Moves.Physical.FlareBlitz;
import Moves.Physical.Spark;

import Moves.Status.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Raichu extends Pokemon{
    public Raichu(){
        super("Молнехвост", 0 );
        setStats(60, 90, 55, 90, 80, 110);
        setType(Type.ELECTRIC);
        setMove(new Confide(), new Swagger(), new Spark(), new TailWhip());
    }
}
