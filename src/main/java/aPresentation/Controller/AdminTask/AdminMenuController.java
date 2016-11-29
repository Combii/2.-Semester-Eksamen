package aPresentation.Controller.AdminTask;

import Dao.AccountDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author samm0091
 * @version 25-11-2016.
 */
public class AdminMenuController {

    @FXML private TextField firstName;

    @FXML private TextField lastName;

    @FXML private TextField userName;

    @FXML private TextField email;

    @FXML private Button confirm;

    @FXML private Button delete;

    @FXML private void handleConfirmButton() throws SQLException {
        //TODO create connection to database
        AccountDao ad = new AccountDao();
        try {

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void handleDeleteButton() throws SQLException, IOException {
        //TODO create connection to database
        AccountDao ad = new AccountDao();
        try {


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
