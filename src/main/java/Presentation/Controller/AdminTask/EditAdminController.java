package Presentation.Controller.AdminTask;

import BusinessLogic.EditAdminBLogic;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author samm0091
 * @version 25-11-2016.
 */
public class EditAdminController {

    private EditAdminBLogic eab;

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
    private void ConfirmButtonClicked() throws SQLException {
        eab = new EditAdminBLogic();
        try {
            if (eab.changeEmail(firstName.getText(), lastName.getText(), email.getText())) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Email has been changed to " + email.getText());
                alert.showAndWait();
            }
            else {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Information Dialog");
                alert2.setHeaderText(null);
                alert2.setContentText("The entered E-mail is not valid");
                alert2.showAndWait();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void DeleteButtonClicked() throws SQLException, IOException {
        eab = new EditAdminBLogic();
        try {
            if (eab.deleteAdmin(firstName.getText())) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Information Dialog");
                alert2.setHeaderText(null);
                alert2.setContentText(firstName.getText() + " has been deleted");
                alert2.showAndWait();
            }
            else {
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setTitle("Information Dialog");
                alert3.setHeaderText(null);
                alert3.setContentText("User doesn't exist");
                alert3.showAndWait();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
