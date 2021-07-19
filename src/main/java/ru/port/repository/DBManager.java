package ru.port.repository;

import java.sql.*;
import java.util.Properties;

public class DBManager {

    private static String url = "jdbc:postgresql://localhost:5432/demo";

    public DBManager() {

    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, "aircraft_user", "aircraft_user");
        return conn;
    }

    public static Connection getConnection1() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "aircraft_user");
        props.setProperty("password", "aircraft_user");
        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }

    public static Connection getConnection2() throws SQLException {
        String url = "jdbc:postgresql://localhost/test?user=aircraft_user&password=aircraft_user";
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = DBManager.getConnection();
        Statement stmt = connection.createStatement();
        Statement stmt1 = connection.prepareCall();
        Statement stmt2 = connection.prepareStatement();
        ResultSet rs = stmt.executeQuery("select * from aircrafts a");
        while (rs.next()) {
            System.out.println("by column index: " + rs.getString(1));
            System.out.println("by column name: " + rs.getString("aircraft_code"));
        }
        System.out.println(connection);
    }
}
