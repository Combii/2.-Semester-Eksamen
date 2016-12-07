package aPresentation.Controller.AdminTask;

import BusinessLogic.Account.UserInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    @FXML
    private TextField name;

    @FXML
    private TableView table;

    @FXML
    private TableColumn<UserInformation, String> columnName;
    //private TableColumn<UserInformation, String> id;

    public AnchorPane anchorPane;

    public void initialize() throws SQLException {
        table.setEditable(true);
    }

    public ObservableList<UserInformation> getUsers(){
        ObservableList<UserInformation> accounts = FXCollections.observableArrayList();
        //accounts.addAll()

        return null;
    }

    public void onMousePressedAnchorPane(MouseEvent mouseEvent) {
        anchorPane.requestFocus();
    }


}
