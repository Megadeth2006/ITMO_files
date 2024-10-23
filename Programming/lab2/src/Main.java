import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import Pokemons.*;
public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        List<Pokemon> pokemons = new ArrayList<>(List.of(new Fearow("Гигачад", 1), new Pichu("Мелочь Пузатая", 1), new Pikachu("Мышь", 1), new Raichu("Ноунейм", 1), new Spearow("Сигма", 1), new Victini("Инопланетянин", 1)));
        Collections.shuffle(pokemons);

        for(Pokemon p : pokemons){
            System.out.printf( "[%d] %s %n", p.getLevel(), p.getClass().getSimpleName());
        }

        System.out.println();


        for (int i = 0; i < pokemons.size(); i++)
            if (i % 2 == 0)
                b.addFoe(pokemons.get(i));
            else
                b.addAlly(pokemons.get(i));

        b.go();

    }
}