package BusinessLogic.Account;

/**
 * Created by David Stovlbaek
 * 29 November 2016.
 */
public class Employee extends Administrator {

    public Employee(String username, String password, int userType, String name, String lastName, String email) {
        super(username,password,userType,name,lastName,email);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
