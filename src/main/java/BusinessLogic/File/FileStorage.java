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
    private List<FilePath> list = new ArrayList<>();

    public void addToList(FilePath file){
        list.add(file);
    }

    public void addFileListToList(List<FilePath> file){
        list.addAll(file);
    }

    public void uploadListToDropbox(String dropboxPathFolder) throws IOException, DbxException {
        if(!list.isEmpty()) {
            DropboxDAO dropboxDAO = new DropboxDAO();
            dropboxDAO.uploadListToDropbox(list);
        }
    }

    public void downloadFilesToList(String dropboxPathFolder) throws IOException, DbxException {
        DropboxDAO dropboxDAO = new DropboxDAO();
    if(list.isEmpty()){
        list = dropboxDAO.downloadFilesFromDropboxToList(dropboxPathFolder);
    }
    else {
        List<FilePath> tempList = dropboxDAO.downloadFilesFromDropboxToList(dropboxPathFolder);
        addFileListToList(tempList);
    }
    }
}
