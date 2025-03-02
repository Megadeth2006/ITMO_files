package Model.form;

import Manager.ScannerManager;
import Manager.ConsoleManager;
import Model.MeleeWeapon;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
//done
public abstract class Form<T> {
    private ConsoleManager console = null;
    private final Scanner scanner = ScannerManager.getScanner();
    public Form(ConsoleManager console) {
        this.console = console;
    }
    public abstract T build();

    public String askString(String fieldName, String restrictions, Predicate<String> validator){
        while (true){
            console.print("Введите " + fieldName + " " + restrictions);
            String input = scanner.nextLine().trim();
            if (validator.test(input)){
                return input;
            }
            else{
                if (input.isEmpty() && validator.test("")){
                    return null;
                }
                else{
                    console.printErr("Неправильный формат ввода");
                }
            }
        }

    }
    public MeleeWeapon askEnum(String field,  Predicate<String> validator){
        while (true){
            console.print("Введите " + field + "\n" + "Доступные значения: \n");
            for (MeleeWeapon value: MeleeWeapon.values()){
                console.print(value.toString() + ", ");
            }

            String input = scanner.nextLine().trim();
            if (validator.test(input)){
                for (MeleeWeapon value: MeleeWeapon.values()){
                    if (value.toString().equals(input.toUpperCase())){
                        return value;
                    }
                }
                console.printErr("Значение не найдено");
            }
            else{
                console.printErr("Неправильный формат ввода");
            }
        }
    }
    public Integer askInt(String field, String restrictions, Predicate<Integer> validator){
        while (true){
            console.print("Введите" + field + " " + restrictions);
            String input = scanner.nextLine().trim();
            try{
                Integer number = Integer.parseInt(input);
                if (validator.test(number)){
                    return number;
                }
                else{
                    console.printErr("Ошибка проверки");
                }
            }
            catch (NumberFormatException e){

                if (input.isEmpty() && validator.test(null)){

                    return 0;
                }
                else{
                    console.printErr("Неверный формат ввода");
                }
            }
        }
    }
    public Long askLong(String field, String restrictions, Predicate<Long> validator){
        while (true){
            console.print("Введите "+ field + " " + restrictions);
            String input = scanner.nextLine().trim();
            try{
                Long number = Long.parseLong(input);
                if (validator.test(number)){
                    return number;
                }
                else{
                    console.printErr("Ошибка проверки");
                }
            }
            catch (NumberFormatException e){
                if (input.isEmpty() && validator.test(null)){
                    return null;
                }
                else{
                    console.printErr("Неверный формат ввода");
                }
            }
        }
    }
    public Double askDouble(String fieldName, String restrictions, Predicate<Double> validator) {
        while (true) {
            console.print("Введите " + fieldName + " " + restrictions);
            String input = scanner.nextLine().trim();
            try {
                Double number = Double.parseDouble(input);
                if (validator.test(number)) {
                    return number;
                } else {
                    console.printErr("Ошибка валидации");
                }
            } catch (NumberFormatException e) {
                if (input.isEmpty() && validator.test(null)) {
                    return null;
                }
                console.printErr("Неверный формат ввода");
            }
        }
    }

}
