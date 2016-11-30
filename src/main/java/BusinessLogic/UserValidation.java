package BusinessLogic;

import Dao.AccountDao;
import Dao.Database;
import java.sql.SQLException;

/**
 * Created by ${Boris} Grunwald} on 25/11/2016.
 */
public class UserValidation {

    //Returns 0 = Admin, 1 = Employee, 2 = Customer, -1 if user is not in DB or incorrect typed username and -2 Password typed is not equal to DB

    public static int isUser(String username, String password) throws SQLException, HashCode.InvalidHashException, HashCode.CannotPerformOperationException {
        AccountDao accountDao = new AccountDao();

        String checkUsername = accountDao.getUsername(username);
        if (checkUsername != null) {
            if (!checkUsername.equals(username))
                return -1;
        } else
            return -1;

        String hashPassword = accountDao.getHashPassword(username);

        if (!HashCode.verifyPassword(password, hashPassword)) {
            return -2;
        }


       return accountDao.getUserTypeByUsername(username);

    }

    public static boolean isCustomer(String password) throws HashCode.CannotPerformOperationException, SQLException {

        AccountDao accountDao = new AccountDao();

        String hashPassword = HashCode.createHash(password);

        int userType = accountDao.getUserTypeByPassword(hashPassword);

        return userType == 2;

    }

    public static boolean isValidUsername(String username) {

        //Must be between 4-20 characters and contain only letters, numbers
        return username.matches("^[\\p{L}\\p{M}*+]{4,20}$");
    }

    public static boolean userExist(String username) throws SQLException{
        AccountDao dao = new AccountDao();
       //Is username already in DB?
     return dao.userExists(username);
    }

    public static boolean isValidPassword(String password) {

        //Must be between 4 and 15 characters, contain at least four numbers and one upper case.
        return password.matches("^(?=.*[\\p{L}\\p{M}*+])(?=.*[0-9]{4,}).{4,15}$");

    }

    public static boolean isValidEmail(String email) {
        //Got from http://emailregex.com/
        return email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");

    }

    public static void startConnectionToDB() throws SQLException {
        Database.startConnection();
    }
}
