import attractions.Carousel;
import attractions.FerrisWheel;
import attractions.Swing;
import enums.AttractionStatus;
import enums.Mood;
import enums.StreetState;
import enums.Time;
import environment.Advertisement;
import environment.City;
import environment.Restaurant;
import environment.Street;
import exceptions.HungryException;
import shorties.Kozlik;
import shorties.Neznaika;
import shorties.OtherShorty;
import shorties.Shorty;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static int id = 1;
    public static double getRandomValue() {
        Random random = new Random();
        return random.nextDouble();
    }
    public static Time getTime(){
        return switch((int)(getRandomValue()*10)){
            case 0, 1, 2 -> Time.MORNING;
            case 3, 4 -> Time.DAY;
            case 5, 6, 7 -> Time.EVENING;
            case 8, 9 -> Time.NIGHT;
            default -> throw new IllegalStateException("Unexpected value: " + (int)(getRandomValue()*10));
        };
    }
    public static Mood getMood(){
        return switch((int)(getRandomValue()*10)){
            case 0, 1, 2, 3 -> Mood.HAPPY;
            case 4, 5, 6 -> Mood.SAD;
            case 7, 8, 9 -> Mood.ENVY;
            default -> throw new IllegalStateException("Unexpected value: " + (int)(getRandomValue()*10));
        };
    }
    public static StreetState getRandState(){
        return switch((int)(getRandomValue()*10)){
            case 0, 1 -> StreetState.HAPPINESS;
            case 2, 3 -> StreetState.NOISY;
            case 4, 5 -> StreetState.MUSICALITY;
            case 6, 7 -> StreetState.ELECTRICAL_LIGHTNING;
            case 8, 9 -> StreetState.SHINE;
            default -> throw new IllegalStateException("Unexpected value: " + (int)(getRandomValue()*10));
        };
    }

    public static AttractionStatus generateStatus(){
        return switch((int)(getRandomValue()*10)){
            case 0, 1, 2 -> AttractionStatus.OPERATIONAL;
            case 3, 4, 5, 6, 7 -> AttractionStatus.NOT_RUNNING;
            case 8, 9 -> AttractionStatus.UNDER_REPAIR;
            default -> throw new IllegalStateException("Unexpected value: " + (int)(getRandomValue()*10));
        };
    }

    public static void main(String[] args) {

        //
        City city = new City("Сан-Комарик");
        Street street1 = new Street("Центральная");
        street1.addAD(new Advertisement(""));
        street1.addAD(new Advertisement("Пепси-Кольная"));
        city.addStreet(street1);
        Restaurant restaurant = new Restaurant();
        //

        for (int i = 0; i < 5 + (int)(Math.random()*10); ++i){
            street1.addShorties(new OtherShorty(getRandomValue() < 0.5, getRandomValue() < 0.5, false, getMood(), id++));
        }


        Kozlik kozlik = new Kozlik(getRandomValue() < 0.5, getRandomValue() < 0.5, false, getMood(), id++);
        Neznaika neznaika = new Neznaika(true, true, false, getMood(), id++);
        street1.addShorties(kozlik);
        street1.addShorties(neznaika);



        Carousel carousel = new Carousel(generateStatus());
        FerrisWheel ferrisWheel = new FerrisWheel(generateStatus());
        Swing swing = new Swing(generateStatus());




        System.out.println("Скоро наступило время: " + getTime().getName());
        System.out.println("Название города, в котором происходят события: " + city.getName());
        System.out.print("Повсюду засветились огни реклам: ");
        for (Advertisement advertisement : street1.getAdvertisement()){
            System.out.print( advertisement.company());
        }
        System.out.println();
        street1.setState(getRandState());
        System.out.println("Улица " + street1.getName() + " наполнилась " + street1.getState().getName());

        if (getRandomValue() < 0.7) {
            for (int i = 0; i < 5 + (int) (Math.random() * 10); ++i) {
                restaurant.addCustomer(new OtherShorty(getRandomValue() < 0.5, getRandomValue() < 0.5, getRandomValue() < 0.5, getMood(), id++));
            }
        }
        System.out.println(restaurant.getCustomers().size() + " коротышек зашли в ресторан");


        ArrayList<Shorty> streetShorties = street1.getShorties();
        System.out.println("На улице всего " + streetShorties.size() + " коротышек");
        for (Shorty currentShorty: streetShorties){
            try {
                currentShorty.walk();
            }catch (HungryException e){
                System.out.println(e.getMessage());
            }
            if (getRandomValue() < 0.6){
                currentShorty.dance();
            }
        }

        if (getRandomValue() < 0.8){
            carousel.setLauncher(new OtherShorty(false, false, false, getMood(), id++));
        }
        if (getRandomValue() < 0.9){
            ferrisWheel.setLauncher(new OtherShorty(false, false, false, getMood(), id++));
        }

        for (int i = 0; i < 5 + (int)(Math.random()*10); ++i){
            carousel.loadUsers(new OtherShorty(false, false, false, getMood(), id++));
        }
        for (int i = 0; i < 5 + (int)(Math.random()*10); ++i){
            ferrisWheel.loadUsers(new OtherShorty(false, false, false, getMood(), id++));
        }

        if (getRandomValue() < 0.7){
            swing.loadUser(new OtherShorty(false, false, false, getMood(), id++));
        }
        carousel.start();
        ferrisWheel.start();
        swing.start();
        if (getRandomValue() < 0.7){
            neznaika.lookAtTheRestaurant();
        }
        if (getRandomValue() < 0.7){
            kozlik.lookAtTheRestaurant();
        }

        if (neznaika.getLookedRestaurant() && !restaurant.getCustomers().isEmpty()){
            System.out.println("Незнайка смотрел на коротышек, находящихся в ресторане, и облизывался");
        }
        else if (restaurant.getCustomers().isEmpty()){
            System.out.println("Незнайка смотрел в окна ресторана и не понимал, почему нет коротышек");
        }
        else{
            System.out.println("Незнайке очень хотелось есть, но он не смотрел в окна ресторана");
        }
        if (kozlik.getLookedRestaurant() && !restaurant.getCustomers().isEmpty()){
            System.out.println("Козлик смотрел на коротышек, находящихся в ресторане, и облизывался");
        }
        else if (restaurant.getCustomers().isEmpty()){
            System.out.println("Козлик смотрел в окна ресторана и не понимал, почему нет коротышек");
        }
        else {System.out.println("Козлик хотел есть, но не смотрел на ресторан");}
    }



}