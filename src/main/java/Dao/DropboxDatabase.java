package Dao;

import com.dropbox.core.*;

import java.io.*;
import java.util.Locale;

/**
 * Created by David Stovlbaek
 * 30 November 2016.
 * Source: https://www.dropbox.com/developers-v1/core/start/java
 */
public class DropboxDatabase {

    private static DbxRequestConfig config;
    private static DbxClient client;
    private static final String accessToken = "dripAUbSyiAAAAAAAAAADfmBh4JsFuVUEq9dLDonN3HXNpLylcdeBNvyIbUW6OGi";
    private static DropboxDatabase dropboxDatabase;

    //Prevents others from creating new instance of this class
    private DropboxDatabase(){}

    public static DropboxDatabase getDropboxDB(){
        if(dropboxDatabase == null) {
            config = new DbxRequestConfig("AnotherCCDropBoxConnection", Locale.getDefault().toString());
            DbxClient client = new DbxClient(config, accessToken);
            dropboxDatabase = new DropboxDatabase();
            return dropboxDatabase;
        }
        else
            return dropboxDatabase;
    }


    public void downloadFromDropbox(String localPathToSave, String dropboxPath) throws IOException, DbxException {
        try (FileOutputStream outputStream = new FileOutputStream(localPathToSave)) {
            DbxEntry.File downloadedFile = client.getFile(dropboxPath, null,
                    outputStream);
            System.out.println("Metadata: " + downloadedFile.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadToDropbox(String localPathToUpload, String dropboxPath) throws IOException, DbxException {
        File inputFile = new File(localPathToUpload);
        try (FileInputStream inputStream = new FileInputStream(inputFile)) {
            DbxEntry.File uploadedFile = client.uploadFile(dropboxPath,
                    DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
