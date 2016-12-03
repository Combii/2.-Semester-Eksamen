package aPresentation.Controller.BrowseMenu;

import BusinessLogic.File.FileStorage;
import Dao.FilePath;
import com.dropbox.core.DbxException;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by David Stovlbaek
 * 26 November 2016.
 */
public class BrowseMenuController {
    public AnchorPane splitPane;
    public GridPane gridPane;

    @FXML
    public void initialize() throws IOException, DbxException, InterruptedException {

        FileStorage list = new FileStorage();
        list.downloadFilesToList("/test");

        int rowCounter = 0, columnCounter = 0;

        for(FilePath i : list.getList()) {
            //System.out.println("/"+i.getLocalPathThumbnail());
            Button button1 = new Button();

            //InputStream in = new(i.getLocalPath());
            Image thumbnail = new Image(i.getLocalPathThumbnail());
            button1.setGraphic(new ImageView(thumbnail));
            gridPane.add(button1, columnCounter, rowCounter);

            columnCounter++;
            if (columnCounter > 3) {
                columnCounter = 0;
                rowCounter++;
            }

        }
        /*
        Button button1 = new Button();

        Image cardA = new Image("/Downloads/test2.jpeg");
        button1.setGraphic(new ImageView(cardA));

        gridPane.add(button1, 0, 0);
        */
    }

}
