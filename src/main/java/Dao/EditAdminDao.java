package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author samm0091
 * @version 29-11-2016.
 */
public class EditAdminDao {

    private Database db;

    public void validateAccount() throws SQLException {

        db = Database.getDatabase();

        String query = "SELECT * FROM customers";
        ResultSet resultSet;

        try {
            resultSet = db.query(query);
            while (resultSet.next()) {
                String userName = resultSet.getString("username");
                System.out.println("The username: " + userName);

                String password = resultSet.getString("password_hash");
                System.out.println("The password: " + password);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAdmin(String firstName) throws SQLException {

        db = Database.getDatabase();
        String query = "SELECT * FROM Account";
        ResultSet resultSet;

        try {
            resultSet = db.query(query);
            while (resultSet.next()) {
                String username = resultSet.getString("username");

                if (username.equals(firstName)) {
                    String sql = "DELETE FROM Account WHERE username =" + "'" + firstName + "'";
                    db.queryUpdate(sql);
                    System.out.println("User deleted");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
