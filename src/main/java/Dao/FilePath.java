package Dao;

import java.io.File;
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
    private String resourcePath = "";
    private String fileType = "";

    public FilePath(String localPath, String dropBoxPath) {
        //------- Resource Path ---------
        resourcePath = new File("src/main/Resources").getAbsolutePath();
        //-------------------------------
        this.localPath = resourcePath + "/Downloads" + localPath;
        this.dropBoxPath = dropBoxPath;
        //Used https://commons.apache.org/proper/commons-io/
        localPathThumbnail = convertStringThumbnail(this.localPath);
        fileType = getFileType(dropBoxPath);
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

    private String getFileType(String path){
        int lastIndex = path.lastIndexOf('.');
        return path.substring(lastIndex+1, path.length());
    }

    private String convertStringThumbnail(String localPath){
        String rString = removeExtension(localPath) + ".jpg";
        int startIndex = localPath.indexOf("Downloads/", 0);
        int lastIndex = rString.lastIndexOf('/');
        return resourcePath + "/" + rString.substring(startIndex, lastIndex) + "/Thumbnail" + rString.substring(lastIndex, rString.length());
    }

    @Override
    public String toString() {
        return  "LocalPath: " + localPath + '\n' +
                "LocalPathThumbnail: " + localPathThumbnail + '\n' +
                "DropBoxPath: " + dropBoxPath + '\n' +
                "FileType: " + fileType;
    }
}
