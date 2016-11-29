package BusinessLogic.Account;

/**
 * Created by David Stovlbaek
 * 29 November 2016.
 */
public class Employee extends Account {

    private String username;
    private String lastName;

    public Employee(String username, String password, int userType, String name, String lastName) {
        super(name, password, userType);
        this.username = username;
        this.lastName = lastName;
    }
}
