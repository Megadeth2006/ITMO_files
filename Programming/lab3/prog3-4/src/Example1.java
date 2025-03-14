import exceptions.ExException;
import exceptions.ExceptionEx2;

public class Example1 {
    public void visRuntimeEx(){
        throw new ExException("34");


    }
    public void visCheckedEx() throws ExceptionEx2{

        throw new ExceptionEx2("sdfs");
    }
}
