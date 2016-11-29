package BusinessLogic;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.Admin;
import BusinessLogic.Account.Employee;
import Dao.AAccountDAO;
import Dao.DAO;

/**
 * Created by David Stovlbaek
 * 29 November 2016.
 */
public class CreateNewAccount {

    private static Account account;

    //UserType 0 = Admin, 1 = Employee, 2 = Customer
    public static void createNewAccount(String username, String password, String email, int userType, String firstName, String lastName){
        if(userType == 0){
            account = new Admin(username, password, userType, firstName, lastName, email);
        }
        else if(userType == 1){
            account = new Employee(username, password, userType, firstName, lastName, email);
        }
        if(account != null){
            saveAccountToDB();
        }
    }

    private static void saveAccountToDB() {
        try{
        DAO<Account> dao = new AAccountDAO();
        dao.save(account);
        }
        catch (Exception e){
          e.printStackTrace();
        }
    }
}
