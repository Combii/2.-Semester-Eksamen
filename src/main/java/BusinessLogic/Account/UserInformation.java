package BusinessLogic.Account;

import BusinessLogic.GeneratePassword;
import Dao.AccountDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lenovo on 28-11-2016.
 */
public class UserInformation {

  /*  public static StringBuilder getUser () throws SQLException{
        AccountDao a = new AccountDao();
        List<String> list = a.getUsers();

        StringBuilder sb = new StringBuilder();
        for(String s : list){
            sb.append(s);
            sb.append("\t");
        }
        return sb;
    }
    */

    public static List getUsers () throws SQLException{
        AccountDao a = new AccountDao();
        List<String> list = a.getUsers();
        return list;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getUsers());
    }
}
