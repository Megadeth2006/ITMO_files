package Pokemons;

import Moves.Physical.Facade;
import Moves.Physical.FlareBlitz;
import Moves.Special.Thunder;
import Moves.Status.WillOWisp;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Victini extends Pokemon {
    public Victini(){
        super("Пришелец", 0);
        setStats(100, 100, 100, 100, 100, 100);
        setType(Type.PSYCHIC, Type.FIRE);
        setMove(new Facade(), new WillOWisp(), new FlareBlitz(), new Thunder());



    }

}
