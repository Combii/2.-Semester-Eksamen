package BusinessLogic;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.List.MyLinkedList;
import Dao.*;

import java.sql.SQLException;

/**
 * Created by ${Boris} Grunwald} on 25/11/2016.
 */
public class UserValidation {

    //Returns 0 = Admin, 1 = Employee, -1 if user is not in DB or incorrect typed username and -2 Password typed is not equal to DB

    public static int isUser(String username, String password) throws SQLException, HashCode.InvalidHashException, HashCode.CannotPerformOperationException {

        AccountDAOInterface dao = new AccountDAO();
        Account acc;


        acc = dao.get(username);

        if(acc == null) return -1;

        int userType = acc.getUserType();

        if(userType == 2 || !HashCode.verifyPassword(password,acc.getPassword())) return -2;

        return userType;

    }

    public static boolean isCustomer(String password) throws SQLException, HashCode.InvalidHashException, HashCode.CannotPerformOperationException {

        AccountDAOInterface dao = new AccountDAO();

        MyLinkedList<String> pass_hashes = dao.getCustomerPasswordHashes();

        for (String pass_hash : pass_hashes) {
            if(HashCode.verifyPassword(password,pass_hash)) return true;
        }

        return false;

    }

    public static void setRememberMe(String username) {

    }

    public static void isRemembered(String macAddress) {

    }

    public static boolean isValidUsername(String username) {

        //Must be between 4-20 characters and contain only letters, numbers
        return username.matches("^[\\p{L}\\p{M}*+]{4,20}$");
    }

    public static boolean userExist(String username) throws SQLException{
        AccountDAOInterface dao = new AccountDAO();
       //Is username already in DB?
     return dao.get(username) != null;

    }

    public static boolean isValidPassword(String password) {

        //Must be between 4 and 15 characters, contain at least 2 numbers and one upper case.
        return password.matches("^(?=.*[\\p{L}\\p{M}*+])(?=.*[0-9]{2,}).{4,15}$");

    }

    public static boolean isValidEmail(String email) {
        //Got from http://emailregex.com/
        return email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");

    }

    public static void startConnectionToDB() throws SQLException {
        SQLDatabase.startConnection();
    }
}
