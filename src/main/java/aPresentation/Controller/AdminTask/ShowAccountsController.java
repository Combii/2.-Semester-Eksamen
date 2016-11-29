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
    //http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/


}
