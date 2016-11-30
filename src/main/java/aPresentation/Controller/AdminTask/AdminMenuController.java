package aPresentation.Controller.AdminTask;

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
public class AdminMenuController {

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
    private void handleConfirmButton() throws SQLException {
        eab = new EditAdminBLogic();
        try {

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteButton() throws SQLException, IOException {
        eab = new EditAdminBLogic();
<<<<<<< HEAD

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
        catch (Exception e)

        {
=======
        try {
            eab.deleteAdmin(firstName.getText());
        }
        catch (Exception e) {
>>>>>>> master
            e.printStackTrace();
        }
    }

}
