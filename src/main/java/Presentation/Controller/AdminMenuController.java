package Presentation.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    private void handleConfirmButton() {
        System.out.println("hej!");
    }

    @FXML
    private void handleDeleteButton() {

    }

}
