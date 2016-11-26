package BusinessLogic;

import Dao.AccountDao;

import java.sql.SQLException;

/**
 * Created by ${Boris} Grunwald} on 25/11/2016.
 */
public class UserValidation {

    //Returns 0 = Admin, 1 = Employee, 2 = Customer and -1 if user is not in DB, -2 Password typed is not equal to DB

   public static int isUser(String username, String password) throws SQLException, HashCode.InvalidHashException, HashCode.CannotPerformOperationException {
            AccountDao accountDao = new AccountDao();

        if(!accountDao.userExists(username)){
            return -1;
        }

        String hashPassword = accountDao.getHashPassword(username);

        if(!HashCode.verifyPassword(password, hashPassword)){
            return -2;
        }

        int userType = accountDao.getUserType(username);

        accountDao.closeConnection();
        return userType;
    }
}
