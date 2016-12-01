package Dao;

import BusinessLogic.File.FileStorage;
import com.dropbox.core.DbxException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        fileStorage = dao.get("/textFiles");
    }

    @Test
    public void uploadListTestSQL() throws IOException, DbxException {
        //list.addLocalFilesToList("/Users/Combii/Desktop/2. Semester Eksamen/AnotherCC/src/main/Resources/Downloads");

        dao.getPathsOfFolder("/src/main/Resources/Downloads");

        List<FilePath> list2 = list.getList();

        //System.out.println(list2);

        //list.uploadListToDropbox("/Test");
    }
}
