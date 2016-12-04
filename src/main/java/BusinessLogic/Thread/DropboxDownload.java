package BusinessLogic.Thread;

import BusinessLogic.File.FileStorage;
import Dao.DropboxDAO;

/**
 * Created by David Stovlbaek
 * 04 December 2016.
 */
public class DropboxDownload implements Runnable {

    private String getString = "";

    public DropboxDownload(String getString) {
        this.getString = getString;
    }


    @Override
    public void run() {
        DropboxDAO dao = new DropboxDAO();
        ListStatic.setList(new FileStorage(dao.get(getString)));
    }
}
