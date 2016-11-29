package Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ${Boris} Grunwald} on 29/11/2016.
 */
public interface DAO<T> {

    void save(T t);
    T get(String name) throws SQLException;
    boolean exists(T t) throws SQLException;
    boolean exists(int id) throws SQLException;
    List<T> findAll() throws SQLException;
    List<T> findAllByName();
    void delete(String id);

}
