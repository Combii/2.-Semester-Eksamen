package Dao;



import BusinessLogic.Account.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boris Grumwald on 25/11/2016.
 */
public class AccountDao implements DAO<Account> {

    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public AccountDao() throws SQLException {
        Database d = Database.getDatabase();
        conn = d.getConnection();
    }

    public boolean userExists(String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT EXISTS(SELECT * FROM Account WHERE username = '" + username + "');");
        ResultSet rs = ps.executeQuery();
        rs.next();

        boolean rBoolean = rs.getBoolean(1);
        closeStatementAndResultsetAndConnection();
        return rBoolean;
    }

    public String getUsername(String username) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT username FROM Account WHERE username = '" + username + "';");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String rString = rs.getString(1);
            closeStatementAndResultsetAndConnection();
            return rString;
        } catch (Exception e) {
            return null;
        }
    }

    public String getHashPassword(String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT password_hash FROM Account WHERE username = '" + username + "';");
        ResultSet rs = ps.executeQuery();
        rs.next();
        String rString = rs.getString(1);
        closeStatementAndResultsetAndConnection();
        return rString;
    }

    public int getUserTypeByUsername(String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT userType FROM Account WHERE username = '" + username + "';");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int rInt = rs.getInt(1);
        closeStatementAndResultsetAndConnection();
        return rInt;
    }


    public int getUserTypeByPassword(String passwordHash) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT userType FROM Account WHERE password_hash = '" + passwordHash + "';");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int rInt = rs.getInt(1);
        closeStatementAndResultsetAndConnection();
        return rInt;

    }

    // Need to close ResultSet, PreparedStatement and connection after use
    private void closeStatementAndResultsetAndConnection() {
        try {
            rs.close();
        } catch (Exception e) { /* ignored */ }
        try {
            ps.close();
        } catch (Exception e) { /* ignored */ }
    }

    public List getUser() throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ID, username FROM Account");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            List<List<String>> list = new ArrayList<List<String>>();

            while (rs.next()) {
                List<String> row = new ArrayList<String>(columnCount);
                int i = 1;
                while (i <= columnCount) {
                    row.add(rs.getString(i++));
                }
                list.add(row);
            }
            closeStatementAndResultsetAndConnection();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void save(Account account) {

    }

    @Override
    public Account get(String name) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Account WHERE username = '" + name + "');");
        ResultSet rs = ps.executeQuery();
        return new Account()

    }

    @Override
    public boolean exists(Account account) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT EXISTS(SELECT * FROM Account WHERE username = '" + account.getUsername() + "');");
        ResultSet rs = ps.executeQuery();
        rs.next();

        boolean rBoolean = rs.getBoolean(1);
        closeStatementAndResultsetAndConnection();
        return rBoolean;
    }

    @Override
    public boolean exists(int id) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT EXISTS(SELECT * FROM Account WHERE ID = '" + id + "');");
        ResultSet rs = ps.executeQuery();
        rs.next();

        boolean rBoolean = rs.getBoolean(1);
        closeStatementAndResultsetAndConnection();
        return rBoolean;

    }

    @Override
    public List<Account> findAll() throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Account;");
        ResultSet rs = ps.executeQuery();

        List<Account> toReturn = new ArrayList<>();


        while (rs.next()) {

        }

    }

    @Override
    public List<Account> findAllByName() {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}

