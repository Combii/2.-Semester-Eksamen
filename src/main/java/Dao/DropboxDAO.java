package Dao;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.apache.commons.io.FilenameUtils.removeExtension;

/**
 * Created by David Stovlbaek
 * 01 December 2016.
 * Source: https://www.dropbox.com/developers/documentation/java#tutorial
 */
public class DropboxDAO implements DAO<List<FilePath>>{
    private DropboxDatabase db = DropboxDatabase.getDropboxDB();
    private DbxClientV2 client = db.getClient();

    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private FilePath downloadFromDropbox(String localPathToSave, String dropboxPath) throws IOException, DbxException {
        FilePath myFile = new FilePath(localPathToSave, dropboxPath);
        File file = new File(myFile.getLocalPath());

        //https://stackoverflow.com/questions/2833853/create-whole-path-automatically-when-writing-to-a-new-file
        //Creates path if doesn't exist
        file.getParentFile().mkdirs();

        OutputStream outputStream = new FileOutputStream(file);
        client.files().download(dropboxPath).download(outputStream);
        downloadThumbnailForFile("src/main/Resources/" + myFile.getLocalPathThumbnail(), myFile.getDropBoxPath());
        return myFile;
    }

    private void uploadToDropbox(String localPathToUpload, String dropboxPath) throws IOException, DbxException, SQLException {
        conn = Database.getDatabase().getConnection();

        ps = conn.prepareStatement("INSERT INTO FilePathDropboxDB VALUES (ID, '" + dropboxPath + "');");
        ps.executeUpdate();

        try (InputStream in = new FileInputStream(localPathToUpload)) {
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
        List<FilePath> tempList = new ArrayList<>();

        try {
            List<String> str = getPathsOfFolderDropbox(dropBoxFolderPath);

            for (String i : str) {
                tempList.add(downloadFromDropbox(i, i));
            }
            return tempList;
        }
        catch (Exception e){
            e.printStackTrace();
        }



        /*
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
        }*/
        return null;
    }

    private void downloadThumbnailForFile(String localPathToSave, String dropboxPath){
        try{
            DbxDownloader dbxDownload = client.files().getThumbnail(dropboxPath);

            //Used https://commons.apache.org/proper/commons-io/
            File filePath = new File(localPathToSave);
            filePath.getParentFile().mkdirs();

            OutputStream out = new FileOutputStream(filePath);
            dbxDownload.download(out);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    private List<FilePath> getPathsOfFolderDropbox(String folderPath) {
        List<FilePath> rList = new ArrayList<>();
        try {
            ListFolderResult result = client.files().listFolder(folderPath);
            while (true) {
                for (Metadata metadata : result.getEntries()) {
                    rList.add(new FilePath("", metadata.getPathLower()));
                }

                if (!result.getHasMore()) {
                    break;
                }
                result = client.files().listFolderContinue(result.getCursor());
            }
            return rList;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    */

    public List<String> getPathsOfFolderDropbox(String path) throws DbxException {
        ListFolderResult list = client.files().listFolder(path);
        List<String> rList = new ArrayList<>();

        for(Metadata i : list.getEntries()){
            rList.add(i.getPathLower());
        }
        return rList;
    }

    public List<FilePath> addLocalFilesToList(String localPathFolder) {
        //https://stackoverflow.com/questions/18444423/get-all-absolute-paths-of-files-under-a-given-folder
        List<FilePath> list = new ArrayList<>();
        listFilesForFolder(new File(localPathFolder), list);
        return list;
    }

    private void listFilesForFolder(final File folder, List<FilePath> list) {
        //https://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry, list);
            } else {
                list.add(new FilePath(fileEntry.getAbsolutePath(),""));
            }
        }
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
    public void delete(int id) throws SQLException {

    }

    public void delete(String dropBoxPath) {
        try{
        ps = conn.prepareStatement("DELETE FROM FilePathDropboxDB WHERE path = '" + dropBoxPath + "');");
        ps.executeUpdate();
        client.files().delete(dropBoxPath);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public DbxClientV2 getClient() {
        return client;
    }
}
