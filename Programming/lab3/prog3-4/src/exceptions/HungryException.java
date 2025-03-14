package exceptions;

public class HungryException extends Exception {

    public HungryException(String message) {
        super(message);
    }
    @Override
    public String getMessage(){
        return "Коротышка голоден, значит не может идти на прогулку";
    }
}