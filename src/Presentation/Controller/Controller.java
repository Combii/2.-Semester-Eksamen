package Presentation.Controller;

import com.sun.tools.javac.comp.Enter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Controller {

    @FXML
    public Button loginButton;
    public Button cancelButton;
    public Label messageLabel;
    public Button noButton;


    @FXML
    public void clickLogin(ActionEvent actionEvent) throws IOException {
        handleButtonAction(actionEvent);
    }

    @FXML
    public void clickCancel(ActionEvent actionEvent) throws IOException {
        handleButtonAction(actionEvent);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent root = null;
        boolean check = true;

        if (event.getSource() == loginButton) {
            //get reference to the button's stage
            stage = (Stage) loginButton.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("/Presentation/Gui/pageAfterLogin.fxml"));
        }
        else if (event.getSource() == cancelButton) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/Gui/AlertBox.fxml"));
            root = fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
            check = false;
        }
        else if (event.getSource() == noButton) {
            System.exit(0);
        }
        if (check) {
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void noButton(ActionEvent actionEvent) throws IOException {
        handleButtonAction(actionEvent);
    }



    @FXML
    public void pressEnter(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case ENTER:
                handleButtonAction(new ActionEvent(loginButton,null));
        }
    }


}
