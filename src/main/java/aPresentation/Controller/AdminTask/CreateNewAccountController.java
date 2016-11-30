package aPresentation.Controller.AdminTask;

import BusinessLogic.CreateNewAccount;
import BusinessLogic.UserValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.sql.SQLException;


/**
 * Created by David Stovlbaek
 * 26 November 2016.
 */
public class CreateNewAccountController{
    public AnchorPane anchorPane;
    public Rectangle rectangle;

    public TextField firstName;
    public TextField lastName;
    public TextField username;
    public TextField password;
    public TextField email;
    public TextField confirmPassword;

    private int userType = 1;

    public Button confirmButton;
    public Text textRightToAdminCheckBox;
    public Text textWarning;

    public void clickedOnConfirmButton(ActionEvent actionEvent) throws SQLException {

    if(!UserValidation.isValidUsername(username.getText())){
        textWarning.setText("Username must be between 4-20 characters and only letters");
    }
    else if(UserValidation.userExist(username.getText())){
        textWarning.setText("Username is already saved in Database");
    }
    else if(!UserValidation.isValidEmail(email.getText())){
            textWarning.setText("Email is not valid");
    }
    else if(!password.getText().equals(confirmPassword.getText())){
        textWarning.setText("Password does not match");
    }
    else if(!UserValidation.isValidPassword(password.getText())){
        textWarning.setText("Password must be between 4-15 characters, least four numbers and one upper case");
    }
    else{
        CreateNewAccount.createNewAccount(username.getText(), password.getText(), email.getText(), userType, firstName.getText(), lastName.getText());
    }
    }

    public void keyPressedFirstName(KeyEvent keyEvent) {
        textWarning.setText("");
        if(keyEvent.getCode().toString().equals("ENTER"))
            try {
                clickedOnConfirmButton(new ActionEvent());
            } catch (Exception e){
                e.printStackTrace();
            }
    }

    public void keyPressedLastName(KeyEvent keyEvent) {
        keyPressedFirstName(keyEvent);
    }

    public void keyPressedUsername(KeyEvent keyEvent) {
        keyPressedFirstName(keyEvent);
    }

    public void keyPressedEmail(KeyEvent keyEvent) {
        keyPressedFirstName(keyEvent);
    }

    public void keyPressedPassword(KeyEvent keyEvent) {
        keyPressedFirstName(keyEvent);
    }

    public void keyPressedConfirmPassword(KeyEvent keyEvent) {
        keyPressedFirstName(keyEvent);
    }

    public void clickedMouseAnchorPane(MouseEvent mouseEvent) {
        anchorPane.requestFocus();
    }

    public void clickedMouseRectangle(MouseEvent mouseEvent) {
        rectangle.requestFocus();
    }

    public void clickedCheckBoxAdmin(ActionEvent actionEvent) {
        if(userType == 1)
            userType = 0;
        else
            userType = 1;
    }
}
