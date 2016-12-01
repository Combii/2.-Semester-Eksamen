package Dao;

import BusinessLogic.File.myFile;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Stovlbaek
 * 01 December 2016.
 * Source: https://www.dropbox.com/developers/documentation/java#tutorial
 */
public class DropboxDAO {
    private DropboxDatabase db = DropboxDatabase.getDropboxDB();
    private DbxClientV2 client = db.getClient();

    public myFile downloadFromDropbox(String localPathToSave, String dropboxPath) throws IOException, DbxException {
        File file = new File("src/main/Resources/Downloads" + localPathToSave);

        //https://stackoverflow.com/questions/2833853/create-whole-path-automatically-when-writing-to-a-new-file
        //Creates path if doesn't exist
        file.getParentFile().mkdirs();

        OutputStream outputStream = new FileOutputStream(file);
        client.files().download(dropboxPath).download(outputStream);
        return new myFile("src/main/Resources/Downloads" + localPathToSave, dropboxPath);
    }

    public void uploadToDropbox(String localPathToUpload, String dropboxPath) throws IOException, DbxException {
        try (InputStream in = new FileInputStream("src/main/Resources/Downloads" + localPathToUpload)) {
            FileMetadata metadata = client.files().uploadBuilder(dropboxPath)
                    .uploadAndFinish(in);
        }
    }

    public void uploadListToDropbox(List<myFile> list) throws IOException, DbxException {
        for(myFile i : list){
            String localPath = i.getLocalPath();
            String dropBoxPath = i.getDropBoxPath();

            if(!localPath.equals("") && !dropBoxPath.equals("")){
                uploadToDropbox(localPath, dropBoxPath);
            }
        }
    }

    public List<myFile> downloadFilesFromDropboxToList(String dropBoxFolderPath) throws DbxException, IOException {
        List<myFile> tempList = new ArrayList<>();

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

    public ListFolderResult getPathsOfFolder(String folderPath) {
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
}
