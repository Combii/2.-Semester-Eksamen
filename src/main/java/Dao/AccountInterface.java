package Dao;

import BusinessLogic.Account.Account;

import java.sql.SQLException;

/**
 * Created by ${Boris} Grunwald} on 01/12/2016.
 */
public interface AccountInterface extends DAO<Account> {

    boolean isCustomer(String password) throws SQLException;
    int getId(String username) throws SQLException;
    boolean exists(String username) throws SQLException;
}
