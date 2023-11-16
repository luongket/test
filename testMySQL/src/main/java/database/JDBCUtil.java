package database;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection c = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://localhost:3305/nhasach";
            String username = "root";
            String password = "22021182";
            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return c;
    }
    public static void closeConnection(Connection c) {
        if(c!=null) {
            try {
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void printInfor(Connection c) {
        if(c!=null) {
            try {
                DatabaseMetaData mtdt = c.getMetaData();
                System.out.println(mtdt.getDatabaseProductName());
                System.out.println(mtdt.getDatabaseProductVersion());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
