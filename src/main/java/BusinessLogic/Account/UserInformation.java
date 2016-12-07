package BusinessLogic.Account;

import Dao.AAccountDAO;
import Dao.AccountDAOInterface;
import Dao.AccountDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lenovo on 28-11-2016.
 */
public class UserInformation {


    public static StringBuilder getUser() throws SQLException{
        AccountDAOInterface a = new AAccountDAO();
        List<List<String>> list = a.getUsers();

        StringBuilder sb = new StringBuilder();
        for(List<String> arr : list){
            sb.append(arr);
            sb.append("\t");
        }
        return sb;
    }


    public static List getUsers () throws SQLException{
        AccountDao a = new AccountDao();
        List<String> list = a.getUsers();
        return list;
    }

}
