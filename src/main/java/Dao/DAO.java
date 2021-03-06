package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ${Boris} Grunwald} on 29/11/2016.
 */
public interface DAO<T> {

    void save(T t) throws SQLException;
    T get(String name) throws SQLException;
    void delete(String id) throws SQLException;


}
