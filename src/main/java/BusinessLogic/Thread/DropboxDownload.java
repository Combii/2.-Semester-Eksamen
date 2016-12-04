package BusinessLogic.Thread;

import Dao.DropboxDAO;
import Dao.FilePath;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by David Stovlbaek
 * 04 December 2016.
 */
public class DropboxDownload implements Callable {

    private String getString = "";

    public DropboxDownload(String getString) {
        this.getString = getString;
    }


    @Override
    public List<FilePath> call() throws Exception {
        DropboxDAO dao = new DropboxDAO();
        return dao.get(getString);
    }
}
