package tool;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/educationm";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "131311";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // MySQL 5.x 驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
