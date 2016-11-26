package aPresentation.Controller;

import BusinessLogic.HashCode;
import BusinessLogic.UserValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    public void clickedOnLoginButton(ActionEvent actionEvent) throws HashCode.CannotPerformOperationException, SQLException, HashCode.InvalidHashException, IOException {
        int number =  UserValidation.isUser(username.getText(), password.getText());
        System.out.println(number);

        Stage stage = null;
        Parent root = null;


        if(number == 0){

            stage =(Stage) loginButton.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("/Admin Task/Browse Menu/BrowseMenu.fxml"));
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void customerLoginCheckBox(ActionEvent actionEvent) {

    }

    public void rememberMeCheckBox(ActionEvent actionEvent) {

    }
}
