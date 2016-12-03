package aPresentation.Controller.BrowseMenu;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;


/**
 * Created by David Stovlbaek
 * 26 November 2016.
 */
public class BrowseMenuController {
    public AnchorPane splitPane;
    public GridPane gridPane;

    @FXML
    public void initialize() throws FileNotFoundException {

        Button button1 = new Button();

        Image cardA = new Image("/Downloads/test2.jpeg");
        button1.setGraphic(new ImageView(cardA));

        gridPane.add(button1, 0, 0);
    }
}
