package aPresentation.Controller.Login;

import BusinessLogic.Account.Account;
import BusinessLogic.HashCode;
import BusinessLogic.UserValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;

/**
 * Created by David Stovlbaek
 * 25 November 2016.
 */
public class LoginController {

    @FXML
    public TextField username;
    public TextField password;
    public Button loginButton;

    public AnchorPane anchorPane;
    public Text textOverLoginButton;

    public Stage stage;
    public Parent root;


    @FXML
    public void initialize() throws SQLException {
        try {
            UserValidation.startConnectionToDB();
        }
        catch (SQLNonTransientConnectionException e){
            textOverLoginButton.setText("Could not connect to Online Database");
        }
    }


    public void clickedOnLoginButton(ActionEvent actionEvent) throws HashCode.CannotPerformOperationException, SQLException, HashCode.InvalidHashException, IOException {
        try {
            int number = UserValidation.isUser(username.getText(), password.getText());

            if (number == 0) {
                Account.setLoggedInUsername(username.getText());
                stage = (Stage) loginButton.getScene().getWindow();
                //load up OTHER FXML document
                root = FXMLLoader.load(getClass().getResource("/Admin Task/BorderPane/BorderPane.fxml"));
                //create a new scene with root and set the stage
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
                stage.setHeight(750);
                stage.setWidth(1000);
                stage.centerOnScreen();
            }
            else if(number == -1){
                textOverLoginButton.setText("Username or Password is incorrect");
                password.clear();
            }
            else if(number == -2){
                textOverLoginButton.setText("Password typed does not match to username");
                password.clear();
            }
        }
        catch (StringIndexOutOfBoundsException e){
            textOverLoginButton.setText("Username or Password is incorrect");
            password.clear();
        }
    }

    public void customerLoginCheckBox(ActionEvent actionEvent) throws IOException {

        stage = (Stage) loginButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Login/CustomerLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void rememberMeCheckBox(ActionEvent actionEvent) {

    }


    public void keyPressedUsername(KeyEvent keyEvent) {
        textOverLoginButton.setText("");
        if(keyEvent.getCode().toString().equals("ENTER"))
            try {
                clickedOnLoginButton(new ActionEvent());
            } catch (Exception e){
            e.printStackTrace();
            }
    }

    public void keyPressedPassword(KeyEvent keyEvent) {
        keyPressedUsername(keyEvent);
    }

    public void onMousePressedAnchorPane(MouseEvent mouseEvent) {
        anchorPane.requestFocus();
    }

}
