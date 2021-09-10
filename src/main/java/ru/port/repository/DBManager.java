package ru.port.repository;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;

public class DBManager {

    private static String url = "jdbc:postgresql://localhost:5432/demo";

    public DBManager() {

    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, "aircraft_user", "aircraft_user");
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        String code = args[1];
        Connection connection = DBManager.getConnection();
        Statement stmt = connection.createStatement();
        String baseSql = "select * from aircrafts a";
        if (StringUtils.isNoneEmpty(code)) {
            baseSql = baseSql + " where a.code = " + code; // "select * from aircrafts a where a.code = 1";
        }
        ResultSet rs = stmt.executeQuery(baseSql);
        while (rs.next()) {
            System.out.println("by column index: " + rs.getString(1));
            System.out.println("by column name: " + rs.getString("aircraft_code"));
        }
        System.out.println(connection);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
