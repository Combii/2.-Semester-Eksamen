package aPresentation.Controller.AdminTask;

import BusinessLogic.Account.UserInformation;
import Dao.AccountDao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.application.Application.launch;

/**
 * Created by Lenovo on 28-11-2016.
 */
public class ShowAccountsController {


    private ComboBox dropDown;
    private TextField name;
    private TableView<UserInformation> table;
    private TableColumn<UserInformation, String> username;
    private TableColumn<UserInformation, String> id;
    private ObservableList<UserInformation> masterData = FXCollections.observableArrayList();

    public ShowAccountsController()throws SQLException {
        masterData.add(new UserInformation());
    }
    private void initialize() {
        // 0. Initialize the columns.
        username.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        id.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<UserInformation> filteredData = new FilteredList<UserInformation>(masterData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<UserInformation> sortedData = new SortedList<UserInformation>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }


}
