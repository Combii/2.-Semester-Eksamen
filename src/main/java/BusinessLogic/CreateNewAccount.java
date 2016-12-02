package BusinessLogic;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.Admin;
import BusinessLogic.Account.Customer;
import BusinessLogic.Account.Employee;
import Dao.AAccountDAO;
import Dao.AccountDAOInterface;

/**
 * Created by David Stovlbaek
 * 29 November 2016.
 */
public class CreateNewAccount {

    private static Account account;

    //UserType 0 = Admin, 1 = Employee, 2 = Customer
    public static void createNewAccount(String username, String password, String email, int userType, String firstName, String lastName) {
        try {
            if (userType == 0) {
                account = new Admin(username, HashCode.createHash(password), userType, firstName, lastName, email);
            } else if (userType == 1) {
                account = new Employee(username, HashCode.createHash(password), userType, firstName, lastName, email);
            }
            if (account != null) {
                saveAccountToDB();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void createNewCustomer(String name, String password, int userType) throws HashCode.CannotPerformOperationException {

        account = new Customer(name,HashCode.createHash(password),userType);
        saveAccountToDB();

    }

    private static void saveAccountToDB() {
        try{
        AccountDAOInterface dao = new AAccountDAO();
        dao.save(account);
        }
        catch (Exception e){
          e.printStackTrace();
        }
    }
}
