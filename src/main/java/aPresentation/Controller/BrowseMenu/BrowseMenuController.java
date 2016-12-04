package aPresentation.Controller.BrowseMenu;

import BusinessLogic.File.FileStorage;
import BusinessLogic.Thread.ListStatic;
import Dao.FilePath;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


/**
 * Created by David Stovlbaek
 * 26 November 2016.
 */
public class BrowseMenuController {
    public AnchorPane splitPane;
    public GridPane gridPane;

    @FXML
    public void initialize() throws Exception {

        FileStorage list = new FileStorage(ListStatic.getList().getList());

        int rowCounter = 0, columnCounter = 0;

        for(FilePath i : list.getList()) {

            Button button1 = new Button();
            Image thumbnail = new Image(i.getLocalPathThumbnail());
            button1.setGraphic(new ImageView(thumbnail));
            gridPane.add(button1, columnCounter, rowCounter);

            columnCounter++;
            if (columnCounter > 3) {
                columnCounter = 0;
                rowCounter++;
            }

        }
    }

}
