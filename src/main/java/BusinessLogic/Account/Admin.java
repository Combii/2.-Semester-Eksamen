package BusinessLogic.Account;

/**
 * Created by David Stovlbaek
 * 29 November 2016.
 */
public class Admin extends Account {

    private String username;
    private String lastName;

    public Admin(String username, String password, int userType, String name, String lastName) {
        super(name, password, userType);
        this.username = username;
        this.lastName = lastName;
    }
}
