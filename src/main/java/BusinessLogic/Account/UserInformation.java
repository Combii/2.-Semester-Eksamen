package BusinessLogic.Account;

import BusinessLogic.GeneratePassword;
import Dao.AAccountDAO;
import Dao.AccountDAOInterface;
import Dao.AccountDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 28-11-2016.
 */
public class UserInformation {

    public static StringBuilder getUser () throws SQLException{
        AccountDAOInterface a = new AAccountDAO();
        List<List<String>> list = a.getUsers();

        StringBuilder sb = new StringBuilder();
        for(List<String> arr : list){
            sb.append(arr);
            sb.append("\t");
        }
        return sb;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getUser().toString());
    }
}
