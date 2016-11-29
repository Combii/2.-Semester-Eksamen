package BusinessLogic;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.Admin;
import BusinessLogic.Account.Customer;
import BusinessLogic.Account.Employee;

/**
 * Created by David Stovlbaek
 * 29 November 2016.
 */
public class CreateNewAccount {

    //UserType 0 = Admin, 1 = Employee, 2 = Customer
    public static void createNewAccount(String username, String password, String email, int userType, String firstName, String lastName){
        if(userType == 0){
            Account newAdmin = new Admin(username, password, userType, firstName, lastName);
        }
        else if(userType == 1){
            Account newEmployee = new Employee(username, password, userType, firstName, lastName);
        }
        else if(userType == 2){

        }
    }
}
