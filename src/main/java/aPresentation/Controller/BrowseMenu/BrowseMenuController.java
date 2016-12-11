package aPresentation.Controller.BrowseMenu;

import BusinessLogic.File.FileStorage;
import Dao.FilePath;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.StringConverter;


import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;


/**
 * Created by David Stovlbaek
 * 26 November 2016.
 */
public class BrowseMenuController implements Initializable {
    public AnchorPane splitPane;
    public GridPane gridPane;
    public ContextMenu contextMenu;

    @FXML
    TreeView<String> treeView;

    Image icon = new Image("/img/folder.png", 20, 20, false, false);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treeView.setEditable(true);
        treeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TreeItem<String> root = new TreeItem<>("Root", new ImageView(icon));
        root.setExpanded(true);

        contextMenu = new ContextMenu();



        MenuItem newF = new MenuItem("Add folder");
        MenuItem del = new MenuItem("Delete folder");
        MenuItem ren = new MenuItem("Rename");

        contextMenu.getItems().addAll(newF, del,ren);
        treeView.setContextMenu(contextMenu);

        //New Folder pressed
        newF.setOnAction(e -> {
            if(treeView.getRoot() == null ) {
                treeView.setRoot(new TreeItem<>("New Folder", new ImageView(icon)));
            } else {
                TreeItem<String> t = treeView.getSelectionModel().getSelectedItem();
                t.getChildren().add(new TreeItem<>("New Folder", new ImageView(icon)));
            }
        });

        //Delete folder pressed
        del.setOnAction(e -> {
                TreeItem<String> t = treeView.getSelectionModel().getSelectedItem();
                if(t == root) treeView.setRoot(null);
                else t.getParent().getChildren().remove(t);
        });

        treeView.setRoot(root);
        setGridPane("pics");
    }


    private void setGridPane(String dropboxFolderPath) {
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
                        } catch (IOException e) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
