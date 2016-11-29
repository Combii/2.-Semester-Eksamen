package aPresentation.Controller.AdminTask;

import BusinessLogic.UserValidation;
import javafx.event.ActionEvent;
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
    

    public Button confirmButton;
    public CheckBox checkBoxAdmin;
    public Text textRightToAdminCheckBox;
    public Text textWarning;

    public void clickedOnConfirmButton(ActionEvent actionEvent) throws SQLException {

    if(!UserValidation.isValidUsername(username.getText())){
        textWarning.setText("Username must be between 8-20 characters and only letters");
    }
    if(!password.getText().equals(confirmPassword.getText())){
        textWarning.setText("Password does not match");
    }
    if(!UserValidation.isValidPassword(password.getText())){
        textWarning.setText("Password must be between 8-15 characters, least four numbers and one upper case");
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
}
