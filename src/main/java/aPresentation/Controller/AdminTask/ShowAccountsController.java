package aPresentation.Controller.AdminTask;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.Administrator;
import BusinessLogic.Account.UserInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 28-11-2016.
 * Changed by David Stovlbaek 7. December 2016
 */
//JavaFX Java GUI Tutorial - 18 - Simple TableView - https://www.youtube.com/watch?v=mtdlX2NMy4M
public class ShowAccountsController {

    public AnchorPane anchorPane;

    public TextField name;
    @FXML public TextField nameOrEmail;

    public TableView table;
    protected TableColumn columnUsername, columnName, columnLastName, columnEmail;

    private List<Account> list = null;

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

    @SuppressWarnings("unchecked")
    public void enteredUsernameOrEmail(KeyEvent keyEvent) throws SQLException {
        String usernameOrEmail = nameOrEmail.getText().toLowerCase();
        try {
            if (!usernameOrEmail.replaceAll(" ", "").equals("")) {
                list = UserInformation.getUserList();

                ObservableList<Account> accounts = FXCollections.observableArrayList();
                accounts.addAll(setNewList(usernameOrEmail));
                table.setItems(accounts);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private List setNewList(String usernameOrEmail){
        try {
            list = UserInformation.getUserList();
            List<Account> rList = new ArrayList<>();

            for (Account i : list) {
                if (i instanceof Administrator) {
                    if (((Administrator) i).getUsername().toLowerCase().contains(usernameOrEmail) || ((Administrator) i).getEmail().contains(usernameOrEmail)) {
                    rList.add(i);
                    }
                }
            }
            return rList;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void onMousePressedAnchorPane(MouseEvent mouseEvent) {
        anchorPane.requestFocus();
    }
}
