package Dao;

import java.sql.*;

public class Database {

    private String url = "jdbc:mysql://sql7.freemysqlhosting.net:3306/";
    private String dbName = "sql7146226";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "sql7146226";
    private String password = "5hrN2C6eYs";

    public Database() {
    }

    public void getUsers() {
        try {
            Connection connection = DriverManager.getConnection(url + dbName, userName, password);
            Statement statement = connection.createStatement();
            //statement.executeUpdate("CREATE TABLE bob(name VARCHAR(255), lastname VARCHAR(255))");
            //statement.executeUpdate("INSERT INTO bob (name, lastname) values ('bob', 'bobsen')");
            ResultSet res = statement.executeQuery("SELECT * FROM bob");
            while (res.next()) {
                System.out.println(res.getString("name"));
                System.out.println(res.getString("lastname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
