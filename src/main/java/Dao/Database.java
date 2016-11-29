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
    private static Statement statement;


    //private constructor to avoid client applications to use constructor
    private Database() {
        try {
            connection = DriverManager.getConnection(url + dbName, userName, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public static ResultSet query(String query) throws SQLException {
        statement = Database.connection.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }

    // method for Data Manipulation (DML)
    public static void queryUpdate(String query) throws SQLException {
        statement = Database.connection.createStatement();
        statement.executeUpdate(query);
    }
}
