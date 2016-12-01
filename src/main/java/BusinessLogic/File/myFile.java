package BusinessLogic.File;

/**
 * Created by David Stovlbaek
 * 30 November 2016.
 */
public class myFile {
    private String localPath = "";
    private String dropBoxPath = "";
    private String fileType = "";

    public myFile(String localPath, String dropBoxPath) {
        this.localPath = localPath;
        this.dropBoxPath = dropBoxPath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public String getDropBoxPath() {
        return dropBoxPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public void setDropBoxPath(String dropBoxPath) {
        this.dropBoxPath = dropBoxPath;
    }
}
