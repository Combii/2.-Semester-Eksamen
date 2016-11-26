package BusinessLogic.Account;

/**
 * Created by David Stovlbaek
 * 26 November 2016.
 */
public class Account {
    private static String username;

    public static String getLoggedInUsername() {
        return username;
    }

    private Account(){}

    public static void setLoggedInUsername(String username) {
        Account.username = username.substring(0, 1).toUpperCase() + username.substring(1);;
    }
}
