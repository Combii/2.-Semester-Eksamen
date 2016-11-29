package aPresentation.Controller.AdminTask;

import Dao.EditAdminDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author samm0091
 * @version 25-11-2016.
 */
public class AdminMenuController {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField userName;

    @FXML
    private TextField email;

    @FXML
    private Button confirm;

    @FXML
    private Button delete;

    @FXML
    private void handleConfirmButton() throws SQLException {
        EditAdminDao ead = new EditAdminDao();
        try {

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteButton() throws SQLException, IOException {
        EditAdminDao ead = new EditAdminDao();
        try {
            ead.deleteAdmin(firstName.getText());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
