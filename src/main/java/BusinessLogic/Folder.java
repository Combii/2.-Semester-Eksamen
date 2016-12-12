package BusinessLogic;

import Dao.FilePath;

import java.util.List;

/**
 * Created by David Stovlbaek
 * 12 December 2016.
 */
public class Folder {
    private String folderName;
    private List<FilePath> filePaths;

    public Folder(String folderName, List<FilePath> filePaths) {
        this.folderName = folderName;
        this.filePaths = filePaths;
    }
}
