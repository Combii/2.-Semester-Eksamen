package Dao;

import com.dropbox.core.*;
import com.dropbox.core.v2.DbxClientV2;

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

    public DbxClientV2 getClient() {
        return client;
    }
}
