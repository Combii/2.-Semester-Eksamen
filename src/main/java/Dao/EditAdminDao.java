package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author samm0091
 * @version 29-11-2016.
 */
public class EditAdminDao {

    private SQLDatabase db;

    public boolean changeEmail(String firstName, String lastName, String emailReplace) throws SQLException {
        db = SQLDatabase.getDatabase();
        String query = "SELECT * FROM UserInformation";
        ResultSet resultSet;

        try {
            resultSet = db.query(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");

                String lastN = resultSet.getString("lastName");

                String emailStart = resultSet.getString("Email");

                if (name.equals(firstName) && lastN.equals(lastName)) {
                    String sql = "UPDATE UserInformation SET Email " + "= REPLACE(Email, " + "'" + emailStart + "'" + ", " + "'" + emailReplace + "'" + ")";
                    db.queryUpdate(sql);
                    return true;
                }
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAdmin(String firstName) throws SQLException {
        db = SQLDatabase.getDatabase();
        String query = "SELECT * FROM Account";
        ResultSet resultSet;

        try {
            resultSet = db.query(query);
            while (resultSet.next()) {
                String username = resultSet.getString("username");

                if (username.equals(firstName)) {
                    String sql = "DELETE FROM Account WHERE username =" + "'" + firstName + "'";
                    db.queryUpdate(sql);
                    return true;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
