package BusinessLogic.Account;

import Dao.AccountDAO;
import Dao.AccountDAOInterface;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lenovo on 28-11-2016.
 */
public class UserInformation {

    private static List<Account> userList = null;

    public static List<Account> getUsers() throws SQLException {
        AccountDAOInterface dao = new AccountDAO();
        userList = dao.getUsers();
        return userList;
    }

    public static List<Account> getUserList() throws SQLException {
        if(userList == null){
            getUsers();
        }
        return userList;
    }
}
