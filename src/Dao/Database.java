package Dao;

import java.sql.*;

/**
 * Created by bitchass on 03-11-2016.
 */
public class Database {

    private String url = "jdbc:mysql://localhost:3306/";
    private String dbName = "erhvervstest";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String password = "JuboKijeCepd1_";

    public Database() {
    }

    public void getUsers() {
        try {
            Connection connection = DriverManager.getConnection(url + dbName, userName, password);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM users");
            while (res.next()) {
                System.out.println(res.getString("userName"));
                System.out.println(res.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
