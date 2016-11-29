package BusinessLogic.Account;

/**
 * Created by David Stovlbaek
 * 29 November 2016.
 */
public abstract class AAccount {
    private String username;
    private String password;
    private int userType;

    public AAccount(String username, String password, int userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
