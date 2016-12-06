package Dao;

import BusinessLogic.Account.*;
import BusinessLogic.Account.List.MyLinkedList;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Boris} Grunwald} on 29/11/2016.
 */
public class AAccountDAO implements AccountDAOInterface {

    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public AAccountDAO() throws SQLException {

        conn = Database.getDatabase().getConnection();

    }

    @Override
    public void save(Account account) throws SQLException {

        String AccValuesCustomer = formatValues(new Object[]{account.getName(),account.getPassword(),account.getUserType()},true);

        if (account instanceof Customer) {

            ps = conn.prepareStatement("INSERT INTO Account (username, password_hash,userType) VALUES " + AccValuesCustomer + ";");
            ps.executeUpdate();

        } else {

            Administrator a = (Administrator) account;
            String AccValuesAdmin = formatValues(new Object[]{a.getUsername(),a.getPassword(),a.getUserType()},true);
            String AdminValues = formatValues(new Object[]{a.getName(),a.getLastName(),a.getEmail()},false);

            ps = conn.prepareStatement("INSERT INTO Account (username, password_hash,userType) VALUES " + AccValuesAdmin + ";");
            ps.executeUpdate();

            //Is needed to check latest ID inserted into AccountDAOInterface. Could also do AUTO_INCREMENT in table UserInformation
            PreparedStatement checkID = conn.prepareStatement("SELECT * FROM Account WHERE ID = (SELECT MAX(ID) FROM Account)");
            ResultSet maxID = checkID.executeQuery();
            maxID.next();
            int id = maxID.getInt(1);

            ps = conn.prepareStatement("INSERT INTO UserInformation VALUES ("+id+", " + AdminValues + ";");
            ps.executeUpdate();

        }

        closeStatementAndResultsetAndConnection();

    }


    @Override
    public Account get(String username) throws SQLException{

        try {
            ps = conn.prepareStatement("SELECT * FROM Account LEFT JOIN UserInformation ON Account.ID = UserInformation.ID WHERE username = '" + username + "';");
            rs = ps.executeQuery();

            rs.next();

            String Usname = rs.getString("username");
            String password_hash = rs.getString("password_hash");
            int userType = rs.getInt("userType");

            if(userType != 2) {
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                String email = rs.getString("Email");
                if(userType == 0) {
                    Admin a = new Admin(Usname,password_hash,userType,name,lastName,email);
                    closeStatementAndResultsetAndConnection();
                    return a;
                } else {
                    Employee e = new Employee(Usname, password_hash, userType, name, lastName, email);
                    closeStatementAndResultsetAndConnection();
                    return e;
                }

            }

            return new Customer(Usname,password_hash,userType);
        } catch (SQLException e) {
            return null;
        }

    }

    @Override
    public int getId(String username) throws SQLException {
        try {
            ps = conn.prepareStatement("SELECT ID FROM Account WHERE username = '"+username+"';");
            rs = ps.executeQuery();
            rs.next();
            int id = rs.getInt("ID");
            closeStatementAndResultsetAndConnection();
            return id;
        } catch (SQLException e) {
            return -1;
        }
    }

    @Override
    public MyLinkedList<String> getCustomerPasswordHashes() {

        MyLinkedList<String> pass_hashes = new MyLinkedList<>();

        try {
            ps = conn.prepareStatement("SELECT password_hash FROM Account WHERE userType = 2;");
            rs = ps.executeQuery();
            while(rs.next()) {
                pass_hashes.add(rs.getString("password_hash"));
            }
            closeStatementAndResultsetAndConnection();
            return pass_hashes;
        } catch (SQLException e) {
            closeStatementAndResultsetAndConnection();
            return null;
        }
    }

    @Override
    public boolean exists(String username) throws SQLException {

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT EXISTS(SELECT * FROM Account WHERE username = '" + username + "');");
            ResultSet rs = ps.executeQuery();
            rs.next();
            boolean exists = rs.getBoolean(1);
            closeStatementAndResultsetAndConnection();
            return exists;
        } catch (SQLException e) {
            return false;
        }
    }


    @Override
    public void delete(int id) throws SQLException {

        ps = conn.prepareStatement("DELETE FROM Account WHERE ID = " + id);
        ps.executeUpdate();
        closeStatementAndResultsetAndConnection();

    }

    @Override
    public List getUsers() throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ID, username FROM Account");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            List<List<String>> list = new ArrayList<List<String>>();

            while (rs.next()) {
                List<String> row = new ArrayList<String>(columnCount);
                int i = 1;
                while (i <= columnCount) {
                    row.add(rs.getString(i++));
                }
                list.add(row);
            }
            closeStatementAndResultsetAndConnection();
            return list;
        } catch (Exception e) {
            return null;
        }
    }


    //Returns a string with values you can use in SQL statement
    private static String formatValues(Object[] s, boolean withStartParenthesis) {

        String start = withStartParenthesis ? "(" : "";

        for (Object i : s) {
           if (i instanceof Number) {
               start += i;
           } else if (i instanceof String) {
               start += "'"+i+"'";
           }
           start += ", ";
        }

        //Remove last comma and space
        start = start.replaceAll(",\\s$","");

        return start+")";

    }

    // Need to close ResultSet, PreparedStatement and connection after use
    private void closeStatementAndResultsetAndConnection() {
        try {
            rs.close();
        } catch (Exception e) { /* ignored */ }
        try {
            ps.close();
        } catch (Exception e) { /* ignored */ }
    }
}
