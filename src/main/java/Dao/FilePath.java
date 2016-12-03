package Dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.io.FilenameUtils.removeExtension;

/**
 * Created by David Stovlbaek
 * 30 November 2016.
 */
public class FilePath {
    private String localPath = "";
    private String localPathThumbnail = "";
    private String dropBoxPath = "";
    private String fileType = "";

    public FilePath(String localPath, String dropBoxPath) {
        if(localPath.length() >= 29 && localPath.substring(0,29).equals("src/main/Resources/Downloads/")) {
            this.localPath = localPath;
        }
        else {
            this.localPath = "src/main/Resources/Downloads" + localPath;
        }
        this.dropBoxPath = dropBoxPath;
        //Used https://commons.apache.org/proper/commons-io/
        localPathThumbnail = convertStringThumbnail(this.localPath);
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

    public String getLocalPathThumbnail() {
        return localPathThumbnail;
    }

    public void setLocalPath(String localPath) {
        if(localPath.substring(0,29).equals("src/main/Resources/Downloads/"))
            this.localPath = localPath;
        else
            this.localPath = "src/main/Resources/Downloads/" + localPath;
    }

    public void setDropBoxPath(String dropBoxPath) {
        this.dropBoxPath = dropBoxPath;
    }

    @Override
    public String toString() {
        return  "LocalPath: " + localPath + '\n' +
                "LocalPathThumbnail: " + localPathThumbnail + '\n' +
                "DropBoxPath: " + dropBoxPath + '\n' +
                "FileType: " + fileType;
    }


    private static String convertStringThumbnail(String localPath){
        String rString = removeExtension(localPath) + ".jpg";
        int lastIndex = rString.lastIndexOf('/');
        return rString.substring(0, lastIndex) + "/Thumbnail" + rString.substring(lastIndex, rString.length());
    }
}
