package Dao;

import java.sql.*;

public class Database{

    private static String url = "jdbc:mysql://sql7.freemysqlhosting.net:3306/";
    private static String dbName = "sql7146226";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String userName = "sql7146226";
    private static String password = "5hrN2C6eYs";

    private static Database database = null;
    private static Connection connection = null;


    //private constructor to avoid client applications to use constructor
    private Database() {}

    public static Database getDatabase() throws SQLException {
        if(database == null){
            database = new Database();
        }
        return database;
    }

    public static void startConnection() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection(url+dbName,userName,password);
        }
    }

    public static void closeConnection() throws SQLException {
        connection.close();
        connection = null;
    }

    public Connection getConnection() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection(url+dbName,userName,password);
        }
        return connection;
    }
}
