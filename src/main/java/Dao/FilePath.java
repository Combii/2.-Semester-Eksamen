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
    private String folder = "";
    private String localPath = "";
    private String localPathThumbnail = "";
    private String dropBoxPath = "";
    private String resourcePath = "";
    private String fileType = "";
    private String localUploadPath = "";

    public FilePath(String dropBoxPath) {
        this(dropBoxPath, dropBoxPath);
    }

    public FilePath(String localPath, String dropBoxPath) {
        localUploadPath = localPath;

        //------- Resource Path ---------
        resourcePath = new File("src/main/Resources").getAbsolutePath();

        if(dropBoxPath.equals("")){
            File file = new File(localPath);
            dropBoxPath = "/" + getFolder(localPath) + "/" + file.getName();
        }
        if(localPath.contains("src/main/Resources"))
            this.localPath = localPath;
        else
            this.localPath = resourcePath + "/Downloads" + localPath;

        //-------------------------------
        this.dropBoxPath = dropBoxPath;
        //Used https://commons.apache.org/proper/commons-io/
        localPathThumbnail = convertStringThumbnail(this.localPath);
        fileType = getFileType(localPath);
        folder = getFolder(dropBoxPath);
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
        else if(!dropBoxPath.contains("/")){
            return "/" + dropBoxPath + "/" + fileType;
        }
        return dropBoxPath;
    }

    public String getLocalPathThumbnail() {
        return localPathThumbnail;
    }

    public String getFolder() {
        return folder;
    }

    public String getLocalUploadPath() {
        return localUploadPath;
    }

    private String getFileType(String path){
        int lastIndex = path.lastIndexOf('/');
        return path.substring(lastIndex+1, path.length());
    }

    private String convertStringThumbnail(String localPath){
        String rString = removeExtension(localPath) + ".jpg";
        int startIndex = localPath.indexOf("Downloads/", 0);
        int lastIndex = rString.lastIndexOf('/');
        return resourcePath + "/" + rString.substring(startIndex, lastIndex) + "/Thumbnail" + rString.substring(lastIndex, rString.length());
    }

    private String getFolder(String path){
        try {
            int lastIndex = path.lastIndexOf("/");
            path = path.substring(0, lastIndex);
            int lastIndex2 = path.lastIndexOf("/");
            return path.substring(lastIndex2 + 1, lastIndex);
        }
        catch (Exception e){
            return path;
        }
    }

    @Override
    public String toString() {
        return  "LocalPath: " + localPath + '\n' +
                "LocalPathThumbnail: " + localPathThumbnail + '\n' +
                "DropBoxPath: " + dropBoxPath + '\n' +
                "FileType: " + fileType;
    }
}
