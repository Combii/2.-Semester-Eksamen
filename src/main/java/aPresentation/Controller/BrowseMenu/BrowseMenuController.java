package aPresentation.Controller.BrowseMenu;

import BusinessLogic.File.FileStorage;
import Dao.FilePath;
import javafx.event.ActionEvent;
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
import java.awt.*;
import java.io.IOException;



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



            for (final FilePath i : list.getList()) {

                Button button = new Button();

                //Handle when button is clicked on
                button.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            //https://stackoverflow.com/questions/5824916/how-do-i-open-an-image-in-the-default-image-viewer-using-java-on-windows
                            File file = new File(i.getLocalPath());
                            Desktop.getDesktop().open(file);
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                });

                File file = new File(i.getLocalPathThumbnail());
                String localUrl = file.toURI().toURL().toString();

                Image thumbnail = new Image(localUrl, false);
                ImageView view = new ImageView(thumbnail);
                view.setFitHeight(100);
                view.setFitWidth(150);
                button.setGraphic(view);
                gridPane.add(button, columnCounter, rowCounter);

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
