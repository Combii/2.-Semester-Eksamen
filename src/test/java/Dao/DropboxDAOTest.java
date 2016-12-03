package Dao;

import BusinessLogic.File.FileStorage;
import aPresentation.Controller.BrowseMenu.BrowseMenuController;
import com.dropbox.core.DbxException;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by David Stovlbaek
 * 01 December 2016.
 */
public class DropboxDAOTest {

    DropboxDAO dao;
    List<FilePath> fileStorage;
    FileStorage list;

    @Before
    public void setUp() throws Exception {
        dao = new DropboxDAO();
        fileStorage = new ArrayList<>();
        list = new FileStorage();
    }

    @Test
    public void downloadFilesFromDropboxToList() throws Exception {
        //fileStorage = dao.get("/test");
        //System.out.println(fileStorage);
        //dao.getPathsOfFolderDropboxV2("/textfiles");
    }

    @Test
    public void uploadListTestSQL() throws IOException, DbxException {
        //list.addLocalFilesToList("/Users/Combii/Desktop/2. Semester Eksamen/AnotherCC/src/main/Resources/Downloads");

        //dao.delete("/Downloads");

        //System.out.println(dao.getPathsOfFolderDropbox("/"));


        FileStorage list = new FileStorage();
        list.downloadFilesToList("/test");

        int rowCounter = 0, columnCounter = 0;

        for (FilePath i : list.getList()) {
            //System.out.println("/"+i.getLocalPathThumbnail());

            //Both snippets should work (don't forget to prepend a slash if using getClass().getResourceAsStream)
            System.out.println("Local: " + i.getLocalPath());
            System.out.println("DropBox: " + i.getDropBoxPath());
            System.out.println("Thumbnail: " + i.getLocalPathThumbnail());

            System.out.println(DropboxDAOTest.class.getResourceAsStream(i.getLocalPath()));
        }
    }
}
