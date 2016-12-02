package Dao;

import BusinessLogic.Account.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ${Boris} Grunwald} on 29/11/2016.
 */
public class AAccountDAO implements AccountDAOInterface {

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

            //Is needed to check latest ID inserted into AccountDAOInterface. Could also do AUTO_INCREMENT in table UserInformation
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

        try {
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
                if(userType == 0) {
                    Admin a = new Admin(Usname,password_hash,userType,name,lastName,email);
                    closeStatementAndResultsetAndConnection();
                    return a;
                } else {
                    Employee e = new Employee(Usname, password_hash, userType, name, lastName, email);
                    closeStatementAndResultsetAndConnection();
                    return e;
                }

            }

            return new Customer(Usname,password_hash,userType);
        } catch (SQLException e) {
            return null;
        }

    }

    @Override
    public int getId(String username) throws SQLException {
        ps = conn.prepareStatement("SELECT ID FROM Account WHERE username = '"+username+"';");
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt("ID");
    }

    @Override
    public boolean isCustomer(String password) {

        try {
            ps = conn.prepareStatement("SELECT userType FROM Account WHERE password_hash = '"+password+"';");
            rs = ps.executeQuery();
            rs.next();
            int userType = rs.getInt("userType");
            closeStatementAndResultsetAndConnection();
            return userType == 2;
        } catch (SQLException e) {
            closeStatementAndResultsetAndConnection();
            return false;
        }
    }

    @Override
    public boolean exists(String username) throws SQLException {

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT EXISTS(SELECT * FROM Account WHERE username = '" + username + "');");
            ResultSet rs = ps.executeQuery();
            rs.next();
            boolean exists = rs.getBoolean(1);
            closeStatementAndResultsetAndConnection();
            return exists;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean exists(int id) throws SQLException {
        return false;
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
