package Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ${Boris} Grunwald} on 29/11/2016.
 */
public interface DAO<T> {

    void save(T t) throws SQLException;
    T get(String name) throws SQLException;
    boolean exists(int id) throws SQLException;
    void delete(String id) throws SQLException;

}
