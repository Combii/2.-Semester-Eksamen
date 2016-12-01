package Dao;

import BusinessLogic.File.FilePath;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Stovlbaek
 * 01 December 2016.
 */
public class DropboxDAOTest {

    DropboxDAO dao;
    List<FilePath> fileStorage;

    @Before
    public void setUp() throws Exception {
        dao = new DropboxDAO();
        fileStorage = new ArrayList<>();
    }

    @Test
    public void downloadFilesFromDropboxToList() throws Exception {
        fileStorage = dao.downloadFilesFromDropboxToList("/textFiles");
    }
}
