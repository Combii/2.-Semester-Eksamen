package BusinessLogic.File;

import Dao.DropboxDAO;
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

    public List<FilePath> getList() {
        return list;
    }

    public void addToList(FilePath file){
        list.add(file);
    }

    public void addFileListToList(List<FilePath> file){
        list.addAll(file);
    }

    public void uploadListToDropbox(String dropboxPathFolder) throws IOException, DbxException {
        if(!list.isEmpty()) {
            DropboxDAO dropboxDAO = new DropboxDAO();
            dropboxDAO.save(list);
        }
    }

    public void downloadFilesToList(String dropboxPathFolder) throws IOException, DbxException {
        DropboxDAO dropboxDAO = new DropboxDAO();
    if(list.isEmpty()){
        list = dropboxDAO.get(dropboxPathFolder);
    }
    else {
        List<FilePath> tempList = dropboxDAO.get(dropboxPathFolder);
        addFileListToList(tempList);
    }
    }

    public void addLocalFilesToList(String localPathFolder) {
        //https://stackoverflow.com/questions/18444423/get-all-absolute-paths-of-files-under-a-given-folder
            listFilesForFolder(new File(localPathFolder));
    }

    private void listFilesForFolder(final File folder) {
        //https://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                list.add(new FilePath(fileEntry.getAbsolutePath(),""));
            }
        }
    }
}
