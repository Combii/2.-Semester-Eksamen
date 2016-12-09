package aPresentation.Controller.AdminTask;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.UserInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * Created by Lenovo on 28-11-2016.
 * Changed by David Stovlbaek 7. December 2016
 */
//JavaFX Java GUI Tutorial - 18 - Simple TableView - https://www.youtube.com/watch?v=mtdlX2NMy4M
public class ShowAccountsController {

    public AnchorPane anchorPane;

    public TextField name;
    public TextField nameOrEmail;

    public TableView table;
    protected TableColumn columnUsername, columnName, columnLastName, columnEmail;

    @SuppressWarnings("unchecked")
    public void initialize() throws SQLException {
        table.setEditable(true);

        columnUsername = new TableColumn("Username");
        columnUsername.setMinWidth(100);
        columnUsername.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));

        columnName = new TableColumn("Name");
        columnName.setMinWidth(100);
        columnName.setCellValueFactory(new PropertyValueFactory<Account, String>("name"));

        columnLastName = new TableColumn("Last Name");
        columnLastName.setMinWidth(130);
        columnLastName.setCellValueFactory(new PropertyValueFactory<Account, String>("lastName"));

        columnEmail = new TableColumn("E-mail");
        columnEmail.setMinWidth(130);
        columnEmail.setCellValueFactory(new PropertyValueFactory<Account, String>("email"));

        table.setItems(getAccounts());
        table.getColumns().addAll(columnUsername, columnName, columnLastName, columnEmail);
    }

    @SuppressWarnings("unchecked")
    private ObservableList<Account> getAccounts() throws SQLException {
        ObservableList<Account> accounts = FXCollections.observableArrayList();
        accounts.addAll(UserInformation.getUsers());
        return accounts;
    }

    public void onMousePressedAnchorPane(MouseEvent mouseEvent) {
        anchorPane.requestFocus();
    }
    
}
