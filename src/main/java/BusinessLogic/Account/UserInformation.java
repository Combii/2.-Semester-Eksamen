package BusinessLogic.Account;

import Dao.AccountDAO;
import Dao.AccountDAOInterface;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lenovo on 28-11-2016.
 */
public class UserInformation {



    public static void getUsers () throws SQLException{
        AccountDAOInterface a = new AccountDAO();
        List<String> list = a.getUsers();
        //return list;
    }

}
