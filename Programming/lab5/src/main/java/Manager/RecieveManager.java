package Manager;

import Exceptions.BreakRecieve;
import Model.Chapter;
import Model.Coordinates;
import Model.MeleeWeapon;
import Model.SpaceMarine;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
// TODO: перенести логику в формы
public class RecieveManager {

//    private static int id = 0;
//
//    public static SpaceMarine takeSpaceMarine(ConsoleManager console, int id) throws BreakRecieve {
//        try {
//            String name;
//            long health;
//            String achievements;
//            int height;
//            LocalDateTime creationDate;
//            do {
//                console.print("Введите SpaceMarine.name:");
//                name = console.readln().trim();
//                if (name.equals("exit")) throw new BreakRecieve("Выполнен выход");
//            } while (name.isEmpty());
//
//            var coordinates = takeCoordinates(console);
//
//            while (true) {
//                console.print("Введите SpaceMarine.health:");
//                var line = console.readln().trim();
//                if (line.equals("exit")) throw new BreakRecieve("Выполнен выход");
//                if (!line.isEmpty()) {
//                    try {
//                        health = Long.parseLong(line);
//                        break;
//                    } catch (NumberFormatException e) {
//                        console.printErr("Введите число!");
//                    }
//
//                }
//            }
//            achievements = "234";
//            height = 234;
//            var chapter = takeChapter(console);
//            var meleeweapon = takeMeleeWeapon(console);
//
//            return new SpaceMarine(++id, name, coordinates, LocalDateTime.now(), health, achievements, height, meleeweapon, chapter);
//        } catch (NoSuchElementException | IllegalStateException e) {
//            console.printErr("Некорректное значение");
//        }
//
//        return null;
//    }


//    public static Coordinates takeCoordinates(Console console) throws BreakRecieve {
//        try {
//            int x;
//            while (true) {
//                console.print("Введите координату Coordinates.x:");
//                var line = console.readln().trim();
//                if (line.equals("exit")) throw new BreakRecieve("Выполнен выход");
//                if (!line.isEmpty()) {
//                    try {
//                        x = Integer.parseInt(line);
//                        if (x > -549) break;
//                        else console.printErr("Введите число больше -549");
//                    } catch (NumberFormatException e) {
//                        console.printErr("Введите число!");
//
//                    }
//                }
//            }
//            double y;
//            while (true) {
//                console.print(("Введите координату Coordinates.y:"));
//                var line = console.readln().trim();
//                if (line.equals("exit")) throw new BreakRecieve("Выполнен выход");
//                if (!line.isEmpty()) {
//                    try {
//                        y = Double.parseDouble(line);
//                        if (y > -267) break;
//                        else console.printErr("Введите число больше -267");
//                    } catch (NumberFormatException e) {
//                        console.printErr("Введите число!");
//                    }
//                }
//
//            }
//            return new Coordinates(x, y);
//        } catch (NoSuchElementException | IllegalStateException e) {
//            console.printErr("Повторите ввод, ошибка чтения");
//            return null;
//        }
//    }
//
//    public static Chapter takeChapter(Console console) throws BreakRecieve {
//        try {
//            String name;
//            while (true) {
//                console.print("Введите Chapter.name:");
//                name = console.readln().trim();
//                if (name.equals("exit")) throw new BreakRecieve("Выполнен выход");
//                if (!name.isEmpty()) {
//                    break;
//                } else console.printErr("Строка не может быть пустой");
//
//            }
//
//            String parentlegion;
//            while (true) {
//                console.print("Введите Chapter.parentlegion:");
//                parentlegion = console.readln().trim();
//                if (parentlegion.equals("exit")) throw new BreakRecieve("Выполнен выход");
//                if (!parentlegion.isEmpty()) {
//                    break;
//                }
//
//            }
//            return new Chapter(name, parentlegion);
//        } catch (NullPointerException | IllegalStateException e) {
//            console.printErr("Повторите ввод, ошибка чтения");
//            return null;
//        }
//    }
//
//    public static MeleeWeapon takeMeleeWeapon(Console console) throws BreakRecieve {
//        try {
//            MeleeWeapon weapon;
//            while (true) {
//                console.print("Введите Meleeweapon ("  + ") :");
//                var line = console.readln().trim();
//                if (line.equals("exit")) throw new BreakRecieve("Выполнен выход");
//                if (!line.isEmpty()) {
//                    try {
//                        weapon = MeleeWeapon.valueOf(line);
//                        break;
//                    } catch (NullPointerException | IllegalArgumentException e) {
//                        console.printErr("Введите корректно");
//                    }
//                } else {
//                    return null;
//                }
//            }
//            return weapon;
//        } catch (NoSuchElementException | IllegalStateException e) {
//            console.printErr("Повторите ввод, ошибка чтения");
//            return null;
//        }
//
//
//    }

}
