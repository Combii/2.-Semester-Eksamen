package BusinessLogic.File;

import Dao.Dropbox.FolderDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by David Stovlbaek
 * 12 December 2016.
 */
public class Folder {
    
    private static String folderName;
    private static List<String> folderList;

    public static List<String> getFolders(){
        try {
            folderList = new FolderDAO().getFolders();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return folderList;
    }
}
