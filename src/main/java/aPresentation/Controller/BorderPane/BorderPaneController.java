package aPresentation.Controller.BorderPane;


import aPresentation.ActiveAccountInformation.ActiveAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by David Stovlbaek
 * 26 November 2016.
 */
public class BorderPaneController {

    @FXML
    public BorderPane borderPane;
    public Text username;
    public Rectangle rectangleUsername;

    @FXML
    public void initialize() throws IOException {
        changeBorderPaneCenter("/Admin Task/Browse Menu/BrowseMenu.fxml");

        username.setText(ActiveAccount.getLoggedInUsername());
        //Makes rectangle fit with username
        double width = username.getLayoutBounds().getWidth();
        rectangleUsername.setWidth(width+20);
    }

    public void clickedBrowseMenu(ActionEvent actionEvent) throws IOException {
        changeBorderPaneCenter("/Admin Task/Browse Menu/BrowseMenu.fxml");
    }

    public void clickedEditAccounts(ActionEvent actionEvent) {
        changeBorderPaneCenter("/Admin Task/Edit Admin.fxml");
    }

    public void clickedCreateNewAccount(ActionEvent actionEvent) throws IOException {
        changeBorderPaneCenter("/Admin Task/Create new account.fxml");
    }

    public void clickedLogout(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        //load up OTHER FXML document
        Parent root = FXMLLoader.load(getClass().getResource("/Login/Login.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private void changeBorderPaneCenter(String path){
        //Got from https://stackoverflow.com/questions/15885189/javafx-2-setting-pane-with-fxml-dynamically
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            AnchorPane cmdPane = (AnchorPane) fxmlLoader.load();
            try {
                borderPane.setCenter(cmdPane);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
