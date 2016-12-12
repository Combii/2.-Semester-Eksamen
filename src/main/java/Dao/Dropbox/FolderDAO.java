package Dao.Dropbox;

import Dao.DAO;
import Dao.SQLDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ${Boris} Grunwald} on 12/12/2016.
 */
public class FolderDAO implements DAO<String> {

    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public FolderDAO() throws SQLException {

        conn = SQLDatabase.getDatabase().getConnection();

    }

    @Override
    public void save(String folderName) throws SQLException {

        ps = conn.prepareStatement("INSERT INTO Folder (folderName) VALUE '"+folderName+"';");
        ps.executeUpdate();

    }

    @Override
    public String get(String name) throws SQLException {
        return null;
    }

    @Override
    public void delete(String name) throws SQLException {

        ps = conn.prepareStatement("DELETE FROM Folder WHERE folderName = " + name);
        ps.executeUpdate();
        closeStatementAndResultsetAndConnection();

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
