package Dao;

import com.dropbox.core.*;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

import java.io.*;

/**
 * Created by David Stovlbaek
 * 30 November 2016.
 * Source: https://www.dropbox.com/developers/documentation/java#tutorial
 */
public class DropboxDatabase {

    private static final String ACCESS_TOKEN = "dripAUbSyiAAAAAAAAAADfmBh4JsFuVUEq9dLDonN3HXNpLylcdeBNvyIbUW6OGi";
    private static DbxRequestConfig config;
    private static DbxClientV2 client;
    private static DropboxDatabase dropboxDatabase;

    //Prevents others from creating new instance of this class
    private DropboxDatabase(){}

    public static DropboxDatabase getDropboxDB(){
        if(dropboxDatabase == null) {
            config = new DbxRequestConfig("dropbox/AnotherCC", "en_US");
            client = new DbxClientV2(config, ACCESS_TOKEN);
            dropboxDatabase = new DropboxDatabase();
            return dropboxDatabase;
        }
        else
            return dropboxDatabase;
    }

    public void downloadFromDropbox(String localPathToSave, String dropboxPath) throws IOException, DbxException {
        OutputStream outputStream = new FileOutputStream(localPathToSave);
        client.files().download(dropboxPath).download(outputStream);
    }

    public void uploadToDropbox(String localPathToUpload, String dropboxPath) throws IOException, DbxException {
        try (InputStream in = new FileInputStream(localPathToUpload)) {
            FileMetadata metadata = client.files().uploadBuilder(dropboxPath)
                    .uploadAndFinish(in);
        }
    }

    public ListFolderResult getPathsOfFolder(String folderPath) throws DbxException {
        ListFolderResult result = client.files().listFolder(folderPath);
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }
            result = client.files().listFolderContinue(result.getCursor());
        }
        return result;
    }


}
