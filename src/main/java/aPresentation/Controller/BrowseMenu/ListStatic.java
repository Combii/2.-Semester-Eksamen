package aPresentation.Controller.BrowseMenu;

import Dao.FilePath;

import java.util.List;

/**
 * Created by David Stovlbaek
 * 04 December 2016.
 */
public class ListStatic {
    private static List<FilePath> list = null;

    public static List<FilePath> getList() {
        return list;
    }

    public static void setList(List<FilePath> list) {
        ListStatic.list = list;
    }
}
