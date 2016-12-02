package Dao;

import BusinessLogic.File.FileStorage;
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
        fileStorage = dao.get("/textFiles");
    }

    @Test
    public void uploadListTestSQL() throws IOException, DbxException {
        //list.addLocalFilesToList("/Users/Combii/Desktop/2. Semester Eksamen/AnotherCC/src/main/Resources/Downloads");

        //dao.delete("/Downloads");

        //System.out.println(dao.getPathsOfFolderDropbox("/test"));


        DbxClientV2 clientV2 = dao.getClient();

        DbxDownloader dbxDownload = clientV2.files().getThumbnail("/test/sup.jpeg");

        File file = new File("src/main/Resources/Downloads/test2.jpeg");
        file.getParentFile().mkdirs();

        OutputStream out = new FileOutputStream(file);
        dbxDownload.download(out);

        //List<FilePath> list2 = list.getList();

        //System.out.println(list2);

        //list.uploadListToDropbox("/Test");

    }
}
