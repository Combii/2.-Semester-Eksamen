package Dao;

import java.sql.SQLException;

/**
 * Created by David Stovlbaek
 * 28 November 2016.
 */
public interface AccountDaoInterface {
    boolean userExists(String username) throws SQLException;

    String getUsername(String username) throws SQLException;

    String getHashPassword(String username) throws SQLException;

    int getUserTypeByUsername(String username) throws SQLException;

    int getUserTypeByPassword(String passwordHash) throws SQLException;
}
