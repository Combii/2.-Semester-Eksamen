package aPresentation.Controller.BrowseMenu;

import BusinessLogic.File.FileStorage;
import Dao.FilePath;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.File;


/**
 * Created by David Stovlbaek
 * 26 November 2016.
 */
public class BrowseMenuController {
    public AnchorPane splitPane;
    public GridPane gridPane;

    @FXML
    public void initialize() throws Exception {

        FileStorage list = new FileStorage();

        list.downloadFilesToList("/test");

        int rowCounter = 0, columnCounter = 0;

        for(FilePath i : list.getList()) {

            Button button1 = new Button();

            File resourcesDirectoryPath = new File("src/main/Resources");
            File file = new File(resourcesDirectoryPath + i.getLocalPathThumbnail());
            String localUrl = file.toURI().toURL().toString();

            Image thumbnail = new Image(localUrl, false);

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
