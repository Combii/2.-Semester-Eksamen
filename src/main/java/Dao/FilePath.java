package Dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by David Stovlbaek
 * 30 November 2016.
 */
public class FilePath {
    private String localPath = "";
    private String dropBoxPath = "";
    private String fileType = "";

    public FilePath(String localPath, String dropBoxPath) {
        this.localPath = localPath;
        this.dropBoxPath = dropBoxPath;
    }

    public String getLocalPath() {
            if(localPath.equals("") && !dropBoxPath.equals("")){
                localPath = dropBoxPath;
            }
        return localPath;
    }

    public String getDropBoxPath() {
        if(dropBoxPath.equals("") && !localPath.equals("")){
                Pattern p = Pattern.compile(".+(/.+/.+$)");
                Matcher m = p.matcher(localPath);
                m.find();
                dropBoxPath = m.group(1);
        }
        return dropBoxPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public void setDropBoxPath(String dropBoxPath) {
        this.dropBoxPath = dropBoxPath;
    }

    @Override
    public String toString() {
        return  "LocalPath:" + localPath + '\n' +
                "DropBoxPath: " + dropBoxPath + '\n' +
                "FileType: " + fileType;
    }
}
