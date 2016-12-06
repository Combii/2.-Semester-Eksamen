package Dao.Dropbox;

import Dao.DAO;
import Dao.SQLDatabase;
import Dao.FilePath;
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import net.coobird.thumbnailator.Thumbnails;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        try {
            try (InputStream in = new FileInputStream(filePath.getLocalUploadPath())) {
                client.files().uploadBuilder(filePath.getDropBoxPath())
                        .uploadAndFinish(in);
            }

            conn = SQLDatabase.getDatabase().getConnection();
            int id = getIDOfFolder(filePath.getFolder());

            if (id != -1) {
                ps = conn.prepareStatement("INSERT INTO FilePath VALUES ('" + id + "', '" + filePath.getDropBoxPath() + "');");
                ps.executeUpdate();
            } else {
                ps = conn.prepareStatement("INSERT INTO Folder VALUES (ID, '" + filePath.getFolder() + "');");
                ps.executeUpdate();

                id = getIDOfFolder(filePath.getFolder());


                ps = conn.prepareStatement("INSERT INTO FilePath VALUES ('" + id + "', '" + filePath.getDropBoxPath() + "');");
                ps.executeUpdate();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private int getIDOfFolder(String folderName){
        //folderName = folderName.replaceAll("/", "");
        try {
            ps = conn.prepareStatement("SELECT ID FROM Folder WHERE folderName = '"+ folderName +"'");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            rs.close();
            return id;
        }
        catch (Exception e){
            return -1;
        }
    }

    @Override
    public List<FilePath> get(String folderPathDropbox) {
        try {
            return downloadFromDropboxSQL(folderPathDropbox);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<FilePath> downloadFromDropboxSQL(String folder) throws SQLException, IOException, DbxException {
        List<FilePath> tempList = new ArrayList<>();
        conn = SQLDatabase.getDatabase().getConnection();

        try {
            ps = conn.prepareStatement("SELECT path FROM FilePath INNER JOIN Folder ON Folder.ID=FilePath.ID WHERE Folder.folderName = '"+ folder +"'");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String path = rs.getString(1);
                tempList.add(downloadFromDropbox(path, path));
            }
        }
        catch (SQLException e){
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
            convertToThumbnail(myFile.getLocalPathThumbnail(),myFile.getLocalPath());
            //downloadThumbnailForFile(myFile.getLocalPathThumbnail(), myFile.getDropBoxPath());
            outputStream.close();
        }
        return myFile;
    }

    private void convertToThumbnail(String localPathToSave, String originalImagePath) throws IOException {
        //https://github.com/coobird/thumbnailator/wiki/Examples
        File file = new File(localPathToSave);
        file.getParentFile().mkdirs();

        OutputStream os = new FileOutputStream(file);
        Thumbnails.of(originalImagePath)
                .size(200, 200)
                .outputFormat("png")
                .toOutputStream(os);
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
