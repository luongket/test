package test;

import database.JDBCUtil;

import java.sql.*;

import static java.time.temporal.TemporalAdjusters.next;

public class testJDBCUtil {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3305/libraly";
        String username = "root";
        String password = "22021182";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from vocabulary");
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
