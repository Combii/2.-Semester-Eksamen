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
    private final String insertAcc = "INSERT INTO Account (username, password_hash,userType) VALUES ";
    private final String insertUinformation = "INSERT INTO UserInformation VALUES ";
    private final String selAllAccWhere = "SELECT * FROM Account WHERE ";
    private final String selAllUseWhere = "SELECT * FROM UserInformation WHERE ";

    public AAccountDAO() throws SQLException {

        conn = Database.getDatabase().getConnection();

    }

    @Override
    public void save(Account account) throws SQLException {

        String Accvalues = formatValues(new Object[]{account.getName(),account.getPassword(),account.getUserType()},true);

        System.out.println(insertAcc + Accvalues + ";");

        if (account instanceof Customer) {
            ps = conn.prepareStatement(insertAcc + Accvalues + ";");
            ps.executeUpdate();
        } else {
            Administrator a = (Administrator) account;
            String AdminValues = formatValues(new Object[]{a.getUsername(),a.getEmail(),a.getLastName()},false);


            ps = conn.prepareStatement(insertAcc + Accvalues + ";");
            ps.executeUpdate();

            //Is needed to check latest ID inserted into Account. Could also do AUTO_INCREMENT in table UserInformation
            PreparedStatement checkID = conn.prepareStatement(selAllAccWhere + "ID = (SELECT MAX(ID) FROM Account)");
            ResultSet maxID = checkID.executeQuery();
            maxID.next();
            int id = maxID.getInt(1);

            /*
            ps = conn.prepareStatement(insertUinformation + "('" + id + "', " + AdminValues + ";");
            ps.executeUpdate();
            System.out.println(insertUinformation + "('" + id + "', " + AdminValues + ";");*/

            ps = conn.prepareStatement(insertUinformation + "("+id+", " + AdminValues + ";");
            ps.executeUpdate();

        }

        closeStatementAndResultsetAndConnection();

    }

    @Override
    public Account get(String name) throws SQLException {

        ps = conn.prepareStatement(selAllAccWhere + "username = '" + name + "');");
        rs = ps.executeQuery();


    }

    @Override
    public boolean exists(String name) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT EXISTS(SELECT * FROM Account WHERE username = '" + name + "');");
        ResultSet rs = ps.executeQuery();
        rs.next();
        boolean rBoolean = rs.getBoolean(1);
        closeStatementAndResultsetAndConnection();
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
    //Returns a string with values you can use in SQL statement
    private static String formatValues(Object[] s, boolean withStartParenthesis) {

        String start = withStartParenthesis ? "(" : "";

        for (Object i : s) {
           if (i instanceof Number) {
               start += i;
           } else if (i instanceof String) {
               start += "'"+i+"'";
           }
           start += ", ";
        }

        //Remove last comma and space
        start = start.replaceAll(",\\s$","");

        return start+")";

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
}
