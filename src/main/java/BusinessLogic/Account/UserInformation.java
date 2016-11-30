package BusinessLogic.Account;

import Dao.AccountDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lenovo on 28-11-2016.
 */
public class UserInformation {

    public static StringBuilder getUser () throws SQLException{
        AccountDao a = new AccountDao();
        List<String> list = a.getUser();

        StringBuilder sb = new StringBuilder();
        for(String s : list){
            sb.append(s);
            sb.append("\t");
        }
        return sb;
    }
}
