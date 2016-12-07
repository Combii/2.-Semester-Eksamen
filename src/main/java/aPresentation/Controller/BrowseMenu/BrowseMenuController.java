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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.awt.event.MouseEvent;
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

    Image icon = new Image("/img/folder.png",20,20,false,false);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> root = new TreeItem<>("Root", new ImageView(icon));
        root.setExpanded(true);

        TreeItem<String> nodeA = new TreeItem<>("node A", new ImageView(icon));
        TreeItem<String> nodeB = new TreeItem<>("node B", new ImageView(icon));
        TreeItem<String> nodeC = new TreeItem<>("node C", new ImageView(icon));
        root.getChildren().addAll(nodeA,nodeB,nodeC);
        nodeA.setExpanded(true);

        TreeItem<String> nodeA1 = new TreeItem<>("node A 1", new ImageView(icon));
        TreeItem<String> nodeB1 = new TreeItem<>("node B 1", new ImageView(icon));
        TreeItem<String> nodeC1 = new TreeItem<>("node C 1", new ImageView(icon));
        nodeA.getChildren().addAll(nodeA1,nodeB1,nodeC1);

        treeView.setRoot(root);
        setGridPane("pics");
    }
    public void mouseClick(javafx.scene.input.MouseEvent mouseEvent){
        if(mouseEvent.getClickCount() == 2) {
            TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
            System.out.println(item.getValue());
        }
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
