package BusinessLogic.File;

import Dao.Dropbox.DropboxDAO;
import Dao.FilePath;
import com.dropbox.core.DbxException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Stovlbaek
 * 01 December 2016.
 */
public class FileStorage {
    private List<FilePath> list = new ArrayList<>();
    private DropboxDAO dropboxDAO = null;

    public List<FilePath> getList() {
        return list;
    }

    public void setList(List<FilePath> list) {
        this.list = list;
    }

    public void downloadFilesToList(String dropboxPathFolder) throws IOException, DbxException {
        createDropboxDAO();
        list = dropboxDAO.get(dropboxPathFolder);
    }

    public void uploadListToDropbox() throws IOException, DbxException {
        if(!list.isEmpty()) {
            createDropboxDAO();
            dropboxDAO.save(list);
        }
    }

    public void addToList(FilePath file){
        list.add(file);
    }

    public void addLocalFilesToList(String localPathFolder) throws IOException {
        addLocalFilesToList(localPathFolder, "");
    }

    public void addLocalFilesToList(String localPathFolder, String dropboxFolderPath) throws IOException {
        createDropboxDAO();

        File file = new File(localPathFolder);
        for (final File fileEntry : file.listFiles()) {
            if (!fileEntry.isDirectory()) {
                list.add(new FilePath(fileEntry.getAbsolutePath(), dropboxFolderPath)); //Håndter FilePath til dropbox med denne path
            }
        }
    }

    private void createDropboxDAO(){
        if(dropboxDAO == null){
            dropboxDAO = new DropboxDAO();
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
