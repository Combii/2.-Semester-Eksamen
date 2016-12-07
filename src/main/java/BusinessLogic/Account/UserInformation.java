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

<<<<<<< HEAD
  public static StringBuilder getUser () throws SQLException{
        AccountDao a = new AccountDao();
        List<List<String>>list = a.getUsers();

        StringBuilder sb = new StringBuilder();
        for(List<String> s : list){
            sb.append(s);
=======
    public static StringBuilder getUser () throws SQLException{
        AccountDAOInterface a = new AAccountDAO();
        List<List<String>> list = a.getUsers();

        StringBuilder sb = new StringBuilder();
        for(List<String> arr : list){
            sb.append(arr);
>>>>>>> master
            sb.append("\t");
        }
        return sb;
    }

<<<<<<< HEAD


    public static List getUsers () throws SQLException{
        AccountDao a = new AccountDao();
        List<String> list = a.getUsers();
        return list;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getUsers());
=======
    public static void main(String[] args) throws SQLException {
        System.out.println(getUser().toString());
>>>>>>> master
    }
}
