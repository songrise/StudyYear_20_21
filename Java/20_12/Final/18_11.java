package Final;

import java.util.Date;

abstract class User {
    private String userID;
    private String password;

    public abstract void verifyLogin();

}

class Customer extends User {
    private String name;
    private String address;

    @Override
    public void verifyLogin() {
        // TODO Auto-generated method stub

    }

    public void register() {

    }

    public void login() {

    }

    public void placeOrder() {

    }
}

class Order{
    protected int orderID;
    private Customer customer;
    private Date dateCreated;
    private Date dateShipped;
    private String status;

    public void printStatus(){

    }
}
class ShoppingInfo{
    public void update(){

    }
}

public class 18_11{

}
