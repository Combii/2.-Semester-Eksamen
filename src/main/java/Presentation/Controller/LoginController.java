package Presentation.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

/**
 * Created by David Stovlbaek
 * 25 November 2016.
 */
public class LoginController {

        @FXML
        private TextField username;
        private TextField password;
        private Button loginButton;

        @FXML
        private CheckBox customerLoginCheckBox;

        @FXML
        private CheckBox rememberMeCheckBox;

        @FXML
        void clickedOnLoginButton(ActionEvent event) {
        }

        @FXML
        void customerLoginCheckBox(ActionEvent event) {
        }

        @FXML
        void rememberMeCheckBox(ActionEvent event) {
        }

}
