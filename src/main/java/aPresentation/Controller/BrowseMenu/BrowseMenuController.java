package aPresentation.Controller.BrowseMenu;

import BusinessLogic.File.FileStorage;
import Dao.FilePath;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by David Stovlbaek
 * 26 November 2016.
 */
public class BrowseMenuController implements Initializable {
    public AnchorPane splitPane;
    public GridPane gridPane;

    @FXML
    TreeView<String> treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> root = new TreeItem<>("Root");

        TreeItem<String> nodeA = new TreeItem<>("node A");
        TreeItem<String> nodeB = new TreeItem<>("node B");
        TreeItem<String> nodeC = new TreeItem<>("node C");
        root.getChildren().addAll(nodeA,nodeB,nodeC);

        TreeItem<String> nodeA1 = new TreeItem<>("node A 1");
        TreeItem<String> nodeB1 = new TreeItem<>("node B 1");
        TreeItem<String> nodeC1 = new TreeItem<>("node C 1");
        nodeA.getChildren().addAll(nodeA1,nodeB1,nodeC1);

        treeView.setRoot(root);
    }

    @FXML
    public void initialize() throws Exception {
        setGridPane("pics");
    }


    private void setGridPane(String dropboxFolderPath){
        try {
            FileStorage list = new FileStorage();
            list.downloadFilesToList(dropboxFolderPath);

            int rowCounter = 0, columnCounter = 0;

            for (FilePath i : list.getList()) {

                Button button1 = new Button();

                File file = new File(i.getLocalPathThumbnail());
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
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
