package BusinessLogic.File;

import Dao.Dropbox.DropboxDAO;
import Dao.FilePath;
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

    DropboxDAO dropboxDAO = null;

    public List<FilePath> getList() {
        return list;
    }

    public void setList(List<FilePath> list) {
        this.list = list;
    }

    public void addToList(FilePath file){
        list.add(file);
    }

    public void addFileListToList(List<FilePath> file){
        list.addAll(file);
    }

    public void addLocalFilesToList(String localPathFolder){
        createDropboxDAO();
    }

    public void uploadListToDropbox(String dropboxPathFolder) throws IOException, DbxException {
        if(!list.isEmpty()) {
            createDropboxDAO();
            dropboxDAO.save(list);
        }
    }

    public void downloadFilesToList(String dropboxPathFolder) throws IOException, DbxException {
        createDropboxDAO();
        if(list.isEmpty()){
            list = dropboxDAO.get(dropboxPathFolder);
        }
        else {
            List<FilePath> tempList = dropboxDAO.get(dropboxPathFolder);
            addFileListToList(tempList);
        }
    }

    private void createDropboxDAO(){
        if(dropboxDAO == null){
            dropboxDAO = new DropboxDAO();
        }
    }
}
