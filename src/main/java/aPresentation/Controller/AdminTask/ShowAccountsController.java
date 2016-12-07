package aPresentation.Controller.AdminTask;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.Administrator;
import BusinessLogic.Account.UserInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
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

    public AnchorPane anchorPane;

    public TextField name;
    public TextField nameOrEmail;

    public TableView table;
    public TableColumn columnName;

    @SuppressWarnings("unchecked")
    public void initialize() throws SQLException {
        table.setEditable(true);

        columnName = new TableColumn("Hey");
        columnName.setMinWidth(200);

        columnName.setCellValueFactory(new PropertyValueFactory<Administrator, String>("username"));

        table.setItems(getAccounts());
        table.getColumns().add(columnName);
    }

    @SuppressWarnings("unchecked")
    public ObservableList<Administrator> getAccounts() throws SQLException {
        ObservableList<Administrator> accounts = FXCollections.observableArrayList();
        accounts.addAll(UserInformation.getUsers());


        //UserInformation.getUsers()

        return accounts;
    }

    public void onMousePressedAnchorPane(MouseEvent mouseEvent) {
        anchorPane.requestFocus();
    }


}
