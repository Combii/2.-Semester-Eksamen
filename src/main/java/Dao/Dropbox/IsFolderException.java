package Dao.Dropbox;

/**
 * Created by David Stovlbaek on 17/09/16.
 */
public class IsFolderException extends Exception{

    private String path;

    public IsFolderException(String path){
        this.path = path;
    }

    @Override
    public String toString() {
        return "Have to be file. Path is folder: " + path;
    }
}
