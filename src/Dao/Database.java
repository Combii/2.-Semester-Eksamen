package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by bitchass on 03-11-2016.
 */
public class Database {

    private String url = "jdbc:mysql://localhost:3306/";
    private String dbName = "erhvervstest";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String password = "JuboKijeCepd1_";

    private Connection connection;

    public Database() throws SQLException {
        this.connection = DriverManager.getConnection(url + dbName, userName, password);
    }
}
