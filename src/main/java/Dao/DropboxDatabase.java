package Dao;


import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

/**
 * Created by David Stovlbaek
 * 30 November 2016.
 * https://www.dropbox.com/developers/documentation/java#tutorial
 */
public class DropboxDatabase {

    private static final String ACCESS_TOKEN = "dripAUbSyiAAAAAAAAAACZtuHT06tu2vhZi1cjb1beSfaSEq7osLYkUBmKwcSDLY";
    private static DbxClientV2 client = null;
        private static DropboxDatabase dropboxDatabase = null;


    //private constructor to avoid client applications to use constructor
    private DropboxDatabase() {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("AnotherCC", "en_US");
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    public static DropboxDatabase getDropboxDB(){
        if(dropboxDatabase == null){
            dropboxDatabase = new DropboxDatabase();
        }
        return dropboxDatabase;
    }

    public void checkConnectedDropboxDB() throws DbxException {
        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());
    }

    public ListFolderResult getFile(String path) {
        try {
            ListFolderResult result = client.files().listFolder(path);
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
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
