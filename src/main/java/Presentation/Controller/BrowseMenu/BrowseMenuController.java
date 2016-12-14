package Presentation.Controller.BrowseMenu;

import BusinessLogic.File.FileStorage;
import BusinessLogic.File.Folder;
import Dao.FilePath;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;


import javax.xml.soap.Text;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.List;
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

    private String currentFolderOpen = "";

    Image icon = new Image("/img/folder.png", 20, 20, false, false);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpTreeView();
        //setGridPane("pics");
    }

    private void setGridPane(String dropboxFolderPath) {
        currentFolderOpen = dropboxFolderPath;

        gridPane.getChildren().clear();
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

    //https://github.com/buckyroberts/Source-Code-from-Tutorials/blob/master/JavaFX/016_treeView/Main.java
    private void setUpTreeView() {
        List<String> folderList = Folder.getFolders();

        TreeItem<String> root;
        root = new TreeItem<>();

        for(String i : folderList)
            makeBranch(i, root);

        treeView.setRoot(root);
        treeView.setShowRoot(false);
        treeView.getSelectionModel().selectedItemProperty()
                .addListener((v, oldValue, newValue) -> {
                    if (newValue != null)
                        setGridPane(newValue.getValue());
                });
        configurationTree(root);
    }

    //Gotten from http://stackoverflow.com/questions/15792090/javafx-treeview-item-action-event
    private void handleMouseClicked(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();
        // Accept clicks only on node cells, and not on empty spaces of the TreeView
        if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
            String name = (String) ((TreeItem) treeView.getSelectionModel().getSelectedItem()).getValue();
            setGridPane(name);
        }
    }

    private TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title, new ImageView(icon));
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }

    private void configurationTree(TreeItem<String> root){

        treeView.setEditable(true);

        //Source: http://stackoverflow.com/questions/15792090/javafx-treeview-item-action-event
        EventHandler<MouseEvent> mouseEventHandle = this::handleMouseClicked;
        treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);

        //Source: http://docs.oracle.com/javafx/2/ui_controls/tree-view.htm
        final class TextFieldTreeCellImpl extends TreeCell<String> {

            private TextField textField;

            public TextFieldTreeCellImpl() {
            }

            @Override
            public void startEdit() {
                super.startEdit();

                if (textField == null) {
                    createTextField();
                }
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }

            @Override
            public void cancelEdit() {
                super.cancelEdit();
                setText((String) getItem());
                setGraphic(getTreeItem().getGraphic());
            }

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (isEditing()) {
                        if (textField != null) {
                            textField.setText(getString());
                        }
                        setText(null);
                        setGraphic(textField);
                    } else {
                        setText(getString());
                        setGraphic(getTreeItem().getGraphic());
                    }
                }
            }

            private void createTextField() {
                textField = new TextField(getString());
                textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent t) {
                        if (t.getCode() == KeyCode.ENTER) {
                            commitEdit(textField.getText());
                        } else if (t.getCode() == KeyCode.ESCAPE) {
                            cancelEdit();
                        }
                    }
                });
            }

            private String getString() {
                return getItem() == null ? "" : getItem().toString();
            }
        }

        treeView.setCellFactory(new Callback<TreeView<String>,TreeCell<String>>(){
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return new TextFieldTreeCellImpl();
            }
        });

        contextMenu = new ContextMenu();

        MenuItem newF = new MenuItem("Add folder");
        MenuItem del = new MenuItem("Delete folder");

        contextMenu.getItems().addAll(newF, del);
        treeView.setContextMenu(contextMenu);

        //New Folder pressed
        newF.setOnAction(e -> {
            if(treeView.getRoot() == null ) {
                treeView.setRoot(new TreeItem<>("New Folder", new ImageView(icon)));
            } else {
                TreeItem<String> t = treeView.getSelectionModel().getSelectedItem();
                TreeItem<String> newI = new TreeItem<>("New Folder", new ImageView(icon));
                t.getChildren().add(newI);
                newI.getParent().setExpanded(true);
            }
        });

        //Delete folder pressed
        del.setOnAction(e -> {
            TreeItem<String> t = treeView.getSelectionModel().getSelectedItem();
            if(t == root) treeView.setRoot(null);
            else t.getParent().getChildren().remove(t);
        });
    }


    public void dragDrop(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();

        if(db.hasFiles()){
            try {
                FileStorage list = new FileStorage();

                for (File i : db.getFiles()) {
                    list.addToList(new FilePath(i.getAbsolutePath(), currentFolderOpen));
                }
                list.uploadListToDropbox();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        setGridPane(currentFolderOpen);
    }
}
