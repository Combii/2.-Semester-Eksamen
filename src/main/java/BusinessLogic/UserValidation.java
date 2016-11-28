package BusinessLogic;

import Dao.AccountDao;
import Dao.AccountDaoInterface;
import Dao.Database;

import java.sql.SQLException;

/**
 * Created by ${Boris} Grunwald} on 25/11/2016.
 */
public class UserValidation {

    //Returns 0 = Admin, 1 = Employee, 2 = Customer, -1 if user is not in DB or incorrect typed username and -2 Password typed is not equal to DB

    public static int isUser(String username, String password) throws SQLException, HashCode.InvalidHashException, HashCode.CannotPerformOperationException {
        AccountDaoInterface accountDaoInterface = new AccountDao();

        String checkUsername = accountDaoInterface.getUsername(username);
        if (checkUsername != null) {
            if (!checkUsername.equals(username))
                return -1;
        } else
            return -1;

        String hashPassword = accountDaoInterface.getHashPassword(username);

        if (!HashCode.verifyPassword(password, hashPassword)) {
            return -2;
        }


       return accountDaoInterface.getUserTypeByUsername(username);

    }

    public static boolean isCustomer(String password) throws HashCode.CannotPerformOperationException, SQLException {

        AccountDaoInterface accountDaoInterface = new AccountDao();

        String hashPassword = HashCode.createHash(password);

        int userType = accountDaoInterface.getUserTypeByPassword(hashPassword);

        return userType == 2;

    }

    public static boolean isValidUsername(String username) throws SQLException {

        AccountDaoInterface dao = new AccountDao();

        //Is username already in DB?
        //Must be between 8-20 characters and contain only letters, numbers
        return !dao.userExists(username) && username.matches("^[\\p{L}\\p{M}*+]{8,20}$");
    }

    public static boolean isValidPassword(String password) {

        //Must be between 8 and 15 characters, contain at least four numbers and one upper case.
        return password.matches("^(?=.*[A-Z])(?=.*[0-9]{4,}).{8,15}$");

    }

    public static void startConnectionToDB() throws SQLException {
        Database.startConnection();
    }
}
