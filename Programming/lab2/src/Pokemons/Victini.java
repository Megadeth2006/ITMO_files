package Pokemons;

import Moves.Physical.*;
import Moves.Status.*;
import Moves.Special.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Victini extends Pokemon {
    public Victini(String name, int level){
        super(name, level);
        setStats(100, 100, 100, 100, 100, 100);
        setType(Type.PSYCHIC, Type.FIRE);
        setMove(new Facade(), new WillOWisp(), new FlareBlitz(), new Thunder());
    }

}
