package aPresentation.Controller.AdminTask;

import BusinessLogic.Account.UserInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * Created by Lenovo on 28-11-2016.
 */
//http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/
//http://www.java2s.com/Code/Java/JavaFX/AddnewrowtoTableView.htm

public class ShowAccountsController {

    private ComboBox dropDown;
//    private UserInformation userinfo;

    @FXML
    private TextField name;

    @FXML
    private TableView<UserInformation> table;

    @FXML
    private TableColumn<UserInformation, String> columnName;
    //private TableColumn<UserInformation, String> id;

    public AnchorPane anchorPane;

    public void initialize() throws SQLException {
        ObservableList<UserInformation> masterData = FXCollections.observableArrayList(UserInformation.getUsers());
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<UserInformation,String>("name"));

        table.setItems(masterData);
        table.getColumns().addAll(nameCol);
    }

    /*
    public ShowAccountsController()throws SQLException {
        ObservableList<UserInformation> masterData = FXCollections.observableArrayList(UserInformation.getUsers());
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<UserInformation,String>("name"));

        table.setItems(masterData);
        table.getColumns().addAll(nameCol);
    }

*/
    public void onMousePressedAnchorPane(MouseEvent mouseEvent) {
        anchorPane.requestFocus();
    }


}
