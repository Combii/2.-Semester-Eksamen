package aPresentation.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by David Stovlbaek
 * 26 November 2016.
 */
public class BrowseMenuController {
    public MenuItem createNewAccountMenuBar;
    public AnchorPane splitPane;

    public void clickedCreateNewAccountMenuBar(ActionEvent actionEvent) throws IOException {
        Stage stage;
        Parent root;
            stage = (Stage) splitPane.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("/Admin Task/Create admin account.fxml"));
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}
