package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Boris Grumwald on 25/11/2016.
 */
public class AccountDao {

    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public AccountDao() throws SQLException {
        Database d = Database.getDatabase();
        conn = d.getConnection();
    }

    public boolean userExists(String username) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT EXISTS(SELECT * FROM Account WHERE username = '" + username + "');");
        ResultSet rs = ps.executeQuery();
        rs.next();

        boolean rBoolean = rs.getBoolean(1);
        closeStatementAndResultset();
        return rBoolean;
    }

    public String getHashPassword(String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT password_hash FROM Account WHERE username = '" + username + "';");
        ResultSet rs = ps.executeQuery();
        rs.next();
        String rString = rs.getString(1);
        closeStatementAndResultset();
        return rString;
    }

    public int getUserType(String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT userType FROM Account WHERE username = '" + username + "';");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int rInt = rs.getInt(1);
        closeStatementAndResultset();
        return rInt;
    }

    // Good to close connection to database
    public void closeConnection() throws SQLException {
        conn.close();
    }

    // Need to close ResultSet and PreparedStatement after use
    private void closeStatementAndResultset(){
        try { rs.close(); } catch (Exception e) { /* ignored */ }
        try { ps.close(); } catch (Exception e) { /* ignored */ }
    }

}
