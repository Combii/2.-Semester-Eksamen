package BusinessLogic;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.Admin;
import BusinessLogic.Account.Employee;

/**
 * Created by David Stovlbaek
 * 29 November 2016.
 */
public class CreateNewAccount {

    private static Account account;

    //UserType 0 = Admin, 1 = Employee, 2 = Customer
    public static boolean createNewAccount(String username, String password, String email, int userType, String firstName, String lastName){
        if(userType == 0){
            account = new Admin(username, password, userType, firstName, lastName, email);
            return true;
        }
        else if(userType == 1){
            account = new Employee(username, password, userType, firstName, lastName, email);
            return true;
        }
        return false;
    }

    public static Account addAccountToDb(){
        return account;
    }
}
