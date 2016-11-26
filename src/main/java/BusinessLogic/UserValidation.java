package BusinessLogic;

import Dao.AccountDao;

import java.sql.SQLException;

/**
 * Created by ${Boris} Grunwald} on 25/11/2016.
 */
public class UserValidation {

    //Returns 0 = Admin, 1 = Employee, 2 = Customer, -1 if user is not in DB or incorrect typed username and -2 Password typed is not equal to DB

   public static int isUser(String username, String password) throws SQLException, HashCode.InvalidHashException, HashCode.CannotPerformOperationException {
        AccountDao accountDao = new AccountDao();

        String checkUsername = accountDao.getUsername(username);
        if(checkUsername != null){
            if(!checkUsername.equals(username))
                return -1;
        }
        else
            return -1;

        String hashPassword = accountDao.getHashPassword(username);

        if(!HashCode.verifyPassword(password, hashPassword)){
            return -2;
        }

        int userType = accountDao.getUserType(username);

        return userType;
    }
}
