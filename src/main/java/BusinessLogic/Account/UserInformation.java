package BusinessLogic.Account;

import Dao.AccountDAO;
import Dao.AccountDAOInterface;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lenovo on 28-11-2016.
 */
public class UserInformation {



    public static List getUsers() throws SQLException {
        AccountDAOInterface dao = new AccountDAO();
        return dao.getUsers();
    }

}
