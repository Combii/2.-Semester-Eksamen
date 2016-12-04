package BusinessLogic.Thread;

import BusinessLogic.File.FileStorage;
import Dao.FilePath;

import java.util.List;

/**
 * Created by David Stovlbaek
 * 04 December 2016.
 */
public class ListStatic {
    private static FileStorage list = null;

    public static FileStorage getList() {
        return list;
    }

    public static void setList(FileStorage list) {
        ListStatic.list = list;
    }
}
