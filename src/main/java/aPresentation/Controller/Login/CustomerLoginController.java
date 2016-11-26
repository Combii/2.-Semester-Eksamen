package aPresentation.Controller.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by ${Boris} Grunwald} on 26/11/2016.
 */
public class CustomerLoginController {

    Stage stage;
    Parent root;

    @FXML
    private Button loginButton;

    @FXML
    private CheckBox customerLoginCheckbox;

    @FXML
    void clickedOnLoginButton(ActionEvent event) {

    }

    @FXML
    void selectCustomerLogin(ActionEvent event) throws IOException {

        stage = (Stage) customerLoginCheckbox.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/Login/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
