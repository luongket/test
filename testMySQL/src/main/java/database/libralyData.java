package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class libralyData {
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://localhost:3305/libraly";
            String username = "root";
            String password = "22021182";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return connection;
    }
    public static void closeConnection(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void printInfor(Connection connection) {
        if (connection != null) {
            try {
                DatabaseMetaData mtdt = connection.getMetaData();
                System.out.println(mtdt.getDatabaseProductVersion());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
