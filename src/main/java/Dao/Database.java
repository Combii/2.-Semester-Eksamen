package Dao;

import java.sql.*;

public class Database{

    private static SingletonDatabase database = null;

    //private constructor to avoid client applications to use constructor
    private Database(){}

    public static SingletonDatabase getDatabase(){
        if(database == null){
            database = new SingletonDatabase();
        }
        return database;
    }

    private static class SingletonDatabase {
        private String url = "jdbc:mysql://sql7.freemysqlhosting.net:3306/";
        private String dbName = "sql7146226";
        private String driver = "com.mysql.jdbc.Driver";
        private String userName = "sql7146226";
        private String password = "5hrN2C6eYs";


        public Connection getConnection() throws SQLException {

            Connection connection = DriverManager.getConnection(url+dbName,userName,password);
            return connection;
        }
    }
}
