import java.sql.*;

public class Database {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String user = "student";
    private static final String password = "STUDENT";
    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Stabilesc conexiunea...");
            conn = DriverManager.getConnection(DB_URL, user, password);
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            System.out.println("Inchid baza de date...");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}