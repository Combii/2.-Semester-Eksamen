package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Boris Grumwald on 25/11/2016.
 */
public class AccountDao {

    static Connection conn;

    public AccountDao() throws SQLException {
        Database d = Database.getDatabase();
        conn = d.getConnection();
    }

    public static boolean userExists(String username) throws SQLException{
        PreparedStatement statement = conn.prepareStatement("SELECT EXISTS(SELECT * FROM Account WHERE username = '" + username + "');");
        ResultSet set = statement.executeQuery();
        set.next();
        return set.getBoolean(1);
    }

    public static String getHashPassword(String username) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT password_hash FROM Account WHERE username = '" + username + "';");
        ResultSet set = statement.executeQuery();
        set.next();
        return set.getString(1);
    }

    public static int getUserType(String username) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT userType FROM Account WHERE username = '" + username + "';");
        ResultSet set = statement.executeQuery();
        set.next();
        return set.getInt(1);
    }

}
