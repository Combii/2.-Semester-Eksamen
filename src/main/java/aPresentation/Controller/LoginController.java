package aPresentation.Controller;

import BusinessLogic.HashCode;
import BusinessLogic.UserValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by David Stovlbaek
 * 25 November 2016.
 */
public class LoginController {
    public TextField username;
    public TextField password;
    public Button loginButton;
    public Text textOverLoginButton;

    public void clickedOnLoginButton(ActionEvent actionEvent) throws HashCode.CannotPerformOperationException, SQLException, HashCode.InvalidHashException, IOException {
        try {
            int number = UserValidation.isUser(username.getText(), password.getText());

            Stage stage = null;
            Parent root = null;


            if (number == 0) {
                stage = (Stage) loginButton.getScene().getWindow();
                //load up OTHER FXML document
                root = FXMLLoader.load(getClass().getResource("/Admin Task/Browse Menu/BrowseMenu.fxml"));
                //create a new scene with root and set the stage
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else if(number == -1){
                textOverLoginButton.setText("Username or Password is incorrect");
            }
            else if(number == -2){
                textOverLoginButton.setText("Password typed does not match to username");
            }
        }
        catch (NullPointerException e){
            textOverLoginButton.setText("Username or Password is incorrect");
        }

    }

    public void customerLoginCheckBox(ActionEvent actionEvent) {

    }

    public void rememberMeCheckBox(ActionEvent actionEvent) {

    }

}
