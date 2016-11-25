package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by {Boris} Grunwald} on 25/11/2016.
 */
public class UserDao {

    static Connection conn;

    public UserDao() throws SQLException {
        Database d = Database.getDatabase();
        conn = d.getConnection();
    }

    public boolean userExists(String username) throws SQLException{

        PreparedStatement statement = conn.prepareStatement("SELECT EXISTS(SELECT * FROM Account WHERE username = '" + username + "');");
        ResultSet set = statement.executeQuery();
        set.next();
        return set.getBoolean(1);


    }

}
