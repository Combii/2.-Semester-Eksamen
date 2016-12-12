package Dao.Dropbox;

import Dao.DAO;
import Dao.SQLDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Boris} Grunwald} on 12/12/2016.
 */
public class FolderDAO {

    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public FolderDAO() throws SQLException {

        conn = SQLDatabase.getDatabase().getConnection();

    }

    public List<String> getFolders() throws SQLException {
        List<String> rList = new ArrayList<>();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Folder;");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            rList.add(rs.getString(1));
        }
        closeStatementAndResultsetAndConnection();
        return rList;
    }


    public void save(String s) throws SQLException {

        conn.prepareStatement("INSERT INTO Folder (folderName) VALUE ");

    }

    public void delete(int id) throws SQLException {

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
