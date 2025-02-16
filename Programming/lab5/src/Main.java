import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.BreakRecieve;
import Model.*;
import Utility.DefaultConsole;
import Utility.Recieve;

public class Main {
    Scanner scanner = new Scanner(System.in);
    public static ArrayList<SpaceMarine> models = new ArrayList<>();
    public static void main(String[] args) throws BreakRecieve {
        var console = new DefaultConsole();
        models.add(Recieve.takeSpaceMarine(console, 100));
        for (var e: models) System.out.println(e);

    }
}




