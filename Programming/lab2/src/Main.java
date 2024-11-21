import ru.ifmo.se.pokemon.Battle;
import Pokemons.*;
public class Main {
    public static void main(String[] args) {
        Fearow f = new Fearow("", 1);
        System.out.println(f.a);
        System.out.println(Fearow.a);

        Battle b = new Battle();

        b.addFoe(new Fearow("Гигачад", 1));
        b.addAlly(new Pichu("Мелочь Пузатая", 1));
        b.addFoe(new Pikachu("Мышь", 1));
        b.addAlly(new Raichu("Ноунейм", 1));
        b.addFoe(new Spearow("Сигма", 1));
        b.addAlly(new Victini("Инопланетянин", 1));

        b.go();

    }
}