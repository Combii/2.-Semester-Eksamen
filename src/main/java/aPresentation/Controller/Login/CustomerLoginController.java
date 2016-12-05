package aPresentation.Controller.Login;

import BusinessLogic.HashCode;
import BusinessLogic.UserValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ${Boris} Grunwald} on 26/11/2016.
 */
public class CustomerLoginController {

    Stage stage;
    Parent root;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    private Label passwordWasIncorrect;

    @FXML
    private CheckBox customerLoginCheckbox;

    @FXML
    void clickedOnLoginButton(ActionEvent event) throws SQLException, HashCode.CannotPerformOperationException, IOException, HashCode.InvalidHashException {

        try {
            if(UserValidation.isCustomer(passwordField.getText())) {

                stage = (Stage) loginButton.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("/Login/Customermenu.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (SQLException e) {
            passwordWasIncorrect.setText("Password incorrect");
            passwordField.clear();
        }

        passwordWasIncorrect.setText("Password incorrect");
        passwordField.clear();


    }

    @FXML
    void keyPressedPassword(KeyEvent event) throws SQLException, HashCode.CannotPerformOperationException, IOException, HashCode.InvalidHashException {
        if(event.getCode().equals(KeyCode.ENTER)) {
            clickedOnLoginButton(new ActionEvent());
        }
    }

    @FXML
    void selectCustomerLogin(ActionEvent event) throws IOException {

        stage = (Stage) customerLoginCheckbox.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Login/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}
