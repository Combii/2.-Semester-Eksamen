package Dao;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.List.MyLinkedList;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by ${Boris} Grunwald} on 01/12/2016.
 */
public interface AccountDAOInterface extends DAO<Account> {

    MyLinkedList<String> getCustomerPasswordHashes() throws SQLException;

    int getId(String username) throws SQLException;

    boolean exists(String username) throws SQLException;

    List<Account> getUsers() throws SQLException;

    //For remembering a user
    void setRememberMe(String username, String macAddress) throws SQLException;

    //For not remembering a user anymore
    void setRememberMe(String username) throws SQLException;

    //Is this users MAC-Address remembered?
    boolean isRemembered(String macAddress) throws SQLException;

}
