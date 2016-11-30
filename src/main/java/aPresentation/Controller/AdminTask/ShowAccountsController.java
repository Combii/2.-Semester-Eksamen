package aPresentation.Controller.AdminTask;

import BusinessLogic.Account.UserInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

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
