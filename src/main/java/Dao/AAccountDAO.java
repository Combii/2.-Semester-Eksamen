package Dao;

import BusinessLogic.Account.*;

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

        String AccValuesCustomer = formatValues(new Object[]{account.getName(),account.getPassword(),account.getUserType()},true);

        if (account instanceof Customer) {

            ps = conn.prepareStatement("INSERT INTO Account (username, password_hash,userType) VALUES " + AccValuesCustomer + ";");
            ps.executeUpdate();

        } else {

            Administrator a = (Administrator) account;
            String AccValuesAdmin = formatValues(new Object[]{a.getUsername(),a.getPassword(),a.getUserType()},true);
            String AdminValues = formatValues(new Object[]{a.getName(),a.getLastName(),a.getEmail()},false);

            ps = conn.prepareStatement("INSERT INTO Account (username, password_hash,userType) VALUES " + AccValuesAdmin + ";");
            ps.executeUpdate();

            //Is needed to check latest ID inserted into Account. Could also do AUTO_INCREMENT in table UserInformation
            PreparedStatement checkID = conn.prepareStatement("SELECT * FROM Account WHERE ID = (SELECT MAX(ID) FROM Account)");
            ResultSet maxID = checkID.executeQuery();
            maxID.next();
            int id = maxID.getInt(1);

            ps = conn.prepareStatement("INSERT INTO UserInformation VALUES ("+id+", " + AdminValues + ";");
            ps.executeUpdate();

        }

        closeStatementAndResultsetAndConnection();

    }

    @Override
    public Account get(String username) throws SQLException{

            ps = conn.prepareStatement("SELECT * FROM Account LEFT JOIN UserInformation ON Account.ID = UserInformation.ID WHERE username = '" + username + "';");
            rs = ps.executeQuery();

            rs.next();

            String Usname = rs.getString("username");
            String password_hash = rs.getString("password_hash");
            int userType = rs.getInt("userType");

            if(userType != 2) {
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                String email = rs.getString("Email");
                if(userType == 0) return new Admin(Usname,password_hash,userType,name,lastName,email);
                else return new Employee(Usname,password_hash,userType,name,lastName,email);
            }

            return new Customer(Usname,password_hash,userType);

    }

    @Override
    public int getId(String username) throws SQLException {
        ps = conn.prepareStatement("SELECT ID FROM Account WHERE username = '"+username+"';");
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt("ID");
    }

    @Override
    public boolean exists(String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT EXISTS(SELECT * FROM Account WHERE username = '" + username + "');");
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
    public void delete(int id) throws SQLException {

        ps = conn.prepareStatement("DELETE FROM Account WHERE ID = " + id);
        ps.executeUpdate();
        closeStatementAndResultsetAndConnection();

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
