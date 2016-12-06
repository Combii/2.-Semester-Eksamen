package Dao.Dropbox;

import Dao.DAO;
import Dao.SQLDatabase;
import Dao.FilePath;
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Stovlbaek
 * 01 December 2016.
 * Source: https://www.dropbox.com/developers/documentation/java#tutorial
 */
public class DropboxDAO implements DAO<List<FilePath>> {
    private DropboxDatabase db = DropboxDatabase.getDropboxDB();
    private DbxClientV2 client = db.getClient();

    private Connection conn;
    private PreparedStatement ps = null;

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

    private void uploadListToDropbox(List<FilePath> list) {
        try {
            for (FilePath i : list) {
                uploadToDropbox(i);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void uploadToDropbox(FilePath filePath) throws IOException, DbxException, SQLException {
        conn = SQLDatabase.getDatabase().getConnection();

        ps = conn.prepareStatement("INSERT INTO Folder VALUES (ID, '" + filePath.getFolder() + "');");
        ps.executeUpdate();

        ps = conn.prepareStatement("INSERT INTO FilePath VALUES (ID, '" + dropboxPath + "');");
        ps.executeUpdate();

        try (InputStream in = new FileInputStream(localPathToUpload)) {
            client.files().uploadBuilder(dropboxPath)
                    .uploadAndFinish(in);
        }
    }

    @Override
    public List<FilePath> get(String folderPathDropbox) {
        try {
            return downloadFilesFromDropboxToList(folderPathDropbox);
        } catch (DbxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<FilePath> downloadFilesFromDropboxToList(String dropBoxFolderPath) throws DbxException {
        List<FilePath> tempList = new ArrayList<>();

        try {
            ListFolderResult result = client.files().listFolder(dropBoxFolderPath);
            for (Metadata metadata : result.getEntries()) {
                if (!metadata.toString().contains("\".tag\":\"folder\""))
                    tempList.add(downloadFromDropbox(metadata.getPathLower(),metadata.getPathLower()));
                else
                    throw new IsFolderException(metadata.getPathLower());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return tempList;
    }

    private FilePath downloadFromDropbox(String localPathToSave, String dropboxPath) throws IOException, DbxException {
        FilePath myFile = new FilePath(localPathToSave, dropboxPath);
        File file = new File(myFile.getLocalPath());

        if(!file.exists()) {
            //https://stackoverflow.com/questions/2833853/create-whole-path-automatically-when-writing-to-a-new-file
            //Creates path if doesn't exist
            file.getParentFile().mkdirs();

            OutputStream outputStream = new FileOutputStream(file);

            client.files().download(dropboxPath).download(outputStream);
            downloadThumbnailForFile(myFile.getLocalPathThumbnail(), myFile.getDropBoxPath());
            outputStream.close();
        }
        return myFile;
    }

    private void downloadThumbnailForFile(String localPathToSave, String dropboxPath){
        try{
            DbxDownloader dbxDownload = client.files().getThumbnail(dropboxPath);

            //Used https://commons.apache.org/proper/commons-io/
            File filePath = new File(localPathToSave);
            filePath.getParentFile().mkdirs();

            OutputStream out = new FileOutputStream(filePath);
            dbxDownload.download(out);
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
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


}




/*
    private List<FilePath> downloadFilesFromDropboxToList(String dropBoxFolderPath) throws DbxException {
        List<FilePath> tempList = new ArrayList<>();

        try {
            ListFolderResult result = client.files().listFolder(dropBoxFolderPath);
            for (Metadata metadata : result.getEntries()) {
                if (!metadata.toString().contains("\".tag\":\"folder\""))
                    tempList.add(downloadFromDropbox(metadata.getPathLower(),metadata.getPathLower()));
                else
                    tempList.addAll(downloadFilesFromDropboxToList(metadata.getPathLower()));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return tempList;
    }
    */
