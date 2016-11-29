package BusinessLogic;

import Dao.EditAdminDao;

import java.sql.SQLException;

/**
 * @author samm0091
 * @version 29-11-2016.
 */
public class EditAdminBLogic {

    private EditAdminDao ead;

    public void deleteAdmin(String firstName) throws SQLException {
        ead = new EditAdminDao();
        try {
            ead.deleteAdmin(firstName);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void validateAccount() throws SQLException {
        ead = new EditAdminDao();
        try {
            ead.validateAccount();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
