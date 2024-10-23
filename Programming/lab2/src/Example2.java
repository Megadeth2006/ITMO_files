import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Battle;
import Pokemons.Victini;
public class Example2 {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon p1 = new Pokemon("Чужой", 1);
        Pokemon p2 = new Pokemon("Хищник", 1);


        Pokemon v = new Victini();

        b.addAlly(v);
        b.addAlly(p1);
        b.addFoe(p2);
        b.go();

    }
}
