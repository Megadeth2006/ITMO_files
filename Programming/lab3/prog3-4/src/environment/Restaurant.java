package environment;

import shorties.Shorty;
import java.util.List;
import java.util.ArrayList;

public class Restaurant {
    public ArrayList<Shorty> customers = new ArrayList<>();
    public void addCustomer(Shorty shorty){
        this.customers.add(shorty);
    }
    public ArrayList<Shorty> getCustomers(){
        return this.customers;
    }
}
