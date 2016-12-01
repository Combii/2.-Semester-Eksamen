package BusinessLogic.File;

import Dao.DropboxDAO;
import com.dropbox.core.DbxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Stovlbaek
 * 01 December 2016.
 */
public class FileStorage {
    private List<myFile> list = new ArrayList<>();

    public void addToList(myFile file){

    }

    public void addFileListToList(List<myFile> file){

    }

    public void uploadListToDropbox(String dropboxPathFolder){

    }

    public void downloadFilesToList(String dropboxPathFolder) throws IOException, DbxException {
        DropboxDAO dropboxDAO = new DropboxDAO();
    if(list.isEmpty()){
        list = dropboxDAO.downloadFilesFromDropboxToList(dropboxPathFolder);
    }
    else {
        List<myFile> tempList = dropboxDAO.downloadFilesFromDropboxToList(dropboxPathFolder);
        for(myFile i : tempList)
            list.add(i);
    }
    }
}
