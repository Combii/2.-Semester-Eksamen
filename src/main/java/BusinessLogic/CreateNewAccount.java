package BusinessLogic;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.Admin;
import BusinessLogic.Account.Employee;
import Dao.AAccountDAO;
import Dao.DAO;
import javafx.scene.control.Alert;

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
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Account created");
                alert2.setHeaderText(null);
                alert2.setContentText("First name: " + firstName + "\n" +
                        "Last name: " + lastName + "\n" +
                        "Username: " + username + "\n" +
                        "E-mail: " + email);
                alert2.showAndWait();
            }
        }
        catch (Exception e){
            e.printStackTrace();
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
