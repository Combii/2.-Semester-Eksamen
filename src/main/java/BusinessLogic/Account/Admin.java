package BusinessLogic.Account;

/**
 * Created by David Stovlbaek
 * 29 November 2016.
 */
public class Admin extends Account {

    private String username;
    private String email;
    private String lastName;

    public Admin(String username, String password, int userType, String name, String lastName, String email) {
        super(name, password, userType);
        this.username = username;
        this.email = email;
        this.lastName = lastName;
    }
}
