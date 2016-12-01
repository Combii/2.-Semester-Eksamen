package Dao;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;


import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Stovlbaek
 * 01 December 2016.
 * Source: https://www.dropbox.com/developers/documentation/java#tutorial
 */
public class DropboxDAO implements DAO<List<FilePath>>{
    private DropboxDatabase db = DropboxDatabase.getDropboxDB();
    private DbxClientV2 client = db.getClient();

    private FilePath downloadFromDropbox(String localPathToSave, String dropboxPath) throws IOException, DbxException {
        File file = new File("src/main/Resources/Downloads" + localPathToSave);

        //https://stackoverflow.com/questions/2833853/create-whole-path-automatically-when-writing-to-a-new-file
        //Creates path if doesn't exist
        file.getParentFile().mkdirs();

        OutputStream outputStream = new FileOutputStream(file);
        client.files().download(dropboxPath).download(outputStream);
        return new FilePath("src/main/Resources/Downloads" + localPathToSave, dropboxPath);
    }

    private void uploadToDropbox(String localPathToUpload, String dropboxPath) throws IOException, DbxException {
        try (InputStream in = new FileInputStream("src/main/Resources/Downloads" + localPathToUpload)) {
            FileMetadata metadata = client.files().uploadBuilder(dropboxPath)
                    .uploadAndFinish(in);
        }
    }

    private void uploadListToDropbox(List<FilePath> list) {
        try {
            for (FilePath i : list) {
                String localPath = i.getLocalPath();
                String dropBoxPath = i.getDropBoxPath();

                if (!localPath.equals("") && !dropBoxPath.equals("")) {
                    uploadToDropbox(localPath, dropBoxPath);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private List<FilePath> downloadFilesFromDropboxToList(String dropBoxFolderPath) {
        try {
            List<FilePath> tempList = new ArrayList<>();

            ListFolderResult result = client.files().listFolder(dropBoxFolderPath);
            while (true) {
                for (Metadata metadata : result.getEntries()) {
                    String path = metadata.getPathLower();

                    //Downloads files to local folder Downloads in Resources
                    tempList.add(downloadFromDropbox(path, path));
                }

                if (!result.getHasMore()) {
                    break;
                }
                result = client.files().listFolderContinue(result.getCursor());
            }
            return tempList;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private ListFolderResult getPathsOfFolder(String folderPath) {
        try {
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
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(List<FilePath> list) {
        uploadListToDropbox(list);
    }

    @Override
    public List<FilePath> get(String folderPathDropbox) {
       return downloadFilesFromDropboxToList(folderPathDropbox);
    }


    @Override
    public boolean exists(int id) {
        return false;
    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
