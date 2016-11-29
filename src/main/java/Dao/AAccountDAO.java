package Dao;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.Administrator;
import BusinessLogic.Account.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ${Boris} Grunwald} on 29/11/2016.
 */
public class AAccountDAO implements DAO<Account> {

    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public AAccountDAO() throws SQLException {

        conn = Database.getDatabase().getConnection();


    }

    @Override
    public void save(Account account) throws SQLException {

        String name = account.getName();
        String password = account.getPassword();
        int userType = account.getUserType();

        if (account instanceof Customer) {
            ps = conn.prepareStatement("INSERT INTO Account VALUES (ID, '"+name+"','"+password+"',"+userType+");");
            ps.executeUpdate();
        } else {
            Administrator a = (Administrator) account;
            String username = a.getUsername();
            String email = a.getEmail();
            String lastName = a.getLastName();


            ps = conn.prepareStatement("INSERT INTO Account VALUES (ID, '" + username + "','" + password + "'," + userType + ");");
            ps.executeUpdate();


            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Account WHERE ID = (SELECT MAX(ID) FROM Account)");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int id = rs.getInt(1);


            ps = conn.prepareStatement("INSERT INTO UserInformation VALUES ('" + id + "', '" + name + "','" + lastName + "', '" + email + "');");
            ps.executeUpdate();

        }



    }

    @Override
    public Account get(String name) throws SQLException {
        return null;
    }

    @Override
    public boolean exists(String name) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT EXISTS(SELECT * FROM Account WHERE username = '" + name + "');");
        ResultSet rs = ps.executeQuery();
        rs.next();
        boolean rBoolean = rs.getBoolean(1);
        return rBoolean;
    }

    @Override
    public boolean exists(int id) throws SQLException {
        return false;
    }

    @Override
    public List<Account> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Account> findAllByName() {
        return null;
    }

    @Override
    public void delete(String id) throws SQLException {

        ps = conn.prepareStatement("DELETE FROM Account WHERE ID = " + id);
        ps.executeUpdate();

    }
}
