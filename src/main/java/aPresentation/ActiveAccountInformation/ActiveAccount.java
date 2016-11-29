package aPresentation.ActiveAccountInformation;

/**
 * Created by David Stovlbaek
 * 26 November 2016.
 */
public class ActiveAccount {
    private static String username;

    public static String getLoggedInUsername() {
        return username;
    }

    private ActiveAccount(){}

    public static void setLoggedInUsername(String username) {
        ActiveAccount.username = username.substring(0, 1).toUpperCase() + username.substring(1);;
    }
}
