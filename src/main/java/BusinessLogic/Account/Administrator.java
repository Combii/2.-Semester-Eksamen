package BusinessLogic.Account;

import Dao.AAccountDAO;

/**
 * Created by ${Boris} Grunwald} on 29/11/2016.
 */
public class Administrator extends Account {

    private String username;
    private String email;
    private String lastName;

    public Administrator(String username, String password, int userType, String name, String lastName, String email) {
        super(name, password, userType);
        this.username = username;
        this.email = email;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
