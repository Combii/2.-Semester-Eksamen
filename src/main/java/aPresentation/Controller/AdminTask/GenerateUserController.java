package aPresentation.Controller.AdminTask;

import BusinessLogic.CreateNewAccount;
import BusinessLogic.GeneratePassword;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.sql.SQLException;

/**
 * Created by Lenovo on 02-12-2016.
 */
public class GenerateUserController {
    public TextField nameOfDirector;
    public Button button;
    private String generatedPassword;
    private final int userType = 2;
    public AnchorPane anchorPane;

    public void clickedOnButtonOk(ActionEvent actionEvent) throws SQLException {
        try {
            generatedPassword = CreateNewAccount.createNewCustomer(nameOfDirector.getText(),userType);

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("AccountDAOInterface created");
            alert2.setHeaderText(null);
            alert2.setContentText("Name: " + nameOfDirector.getText() + "\n" +
                    "Password" + generatedPassword);
            alert2.showAndWait();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onMousePressedAnchorPane(MouseEvent mouseEvent) {
        anchorPane.requestFocus();
    }

}
