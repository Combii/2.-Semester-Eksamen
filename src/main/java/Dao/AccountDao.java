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
        }
        catch (Exception e){
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

    public int getUserType(String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT userType FROM Account WHERE username = '" + username + "';");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int rInt = rs.getInt(1);
        closeStatementAndResultsetAndConnection();
        return rInt;
    }

    // Need to close ResultSet, PreparedStatement and connection after use
    private void closeStatementAndResultsetAndConnection(){
        try { rs.close(); } catch (Exception e) { /* ignored */ }
        try { ps.close(); } catch (Exception e) { /* ignored */ }
    }

}
