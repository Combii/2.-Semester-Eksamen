package aPresentation.Controller;

import BusinessLogic.PasswordStorage;
import BusinessLogic.UserValidation;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * Created by David Stovlbaek
 * 25 November 2016.
 */
public class LoginController {
    public TextField username;
    public TextField password;

    public void clickedOnLoginButton(ActionEvent actionEvent) throws PasswordStorage.CannotPerformOperationException, SQLException, PasswordStorage.InvalidHashException {
        int number =  UserValidation.isUser(username.getText(), password.getText());
    }

    public void customerLoginCheckBox(ActionEvent actionEvent) {

    }

    public void rememberMeCheckBox(ActionEvent actionEvent) {

    }
}
