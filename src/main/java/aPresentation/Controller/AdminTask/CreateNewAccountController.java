package aPresentation.Controller.AdminTask;

import BusinessLogic.Account.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


/**
 * Created by David Stovlbaek
 * 26 November 2016.
 */
public class CreateNewAccountController{
    public TextField firstName, lastName, username, email, password, confirmPassword;

    public Button confirmButton;
    public CheckBox checkBoxAdmin;
    public Text textRightToAdminCheckBox;

    public Text usernameMenuBar;

    @FXML
    public void initialize() {
        usernameMenuBar.setText(Account.getLoggedInUsername());
    }

    public void clickedOnConfirmButton(ActionEvent actionEvent) {

    }
}
