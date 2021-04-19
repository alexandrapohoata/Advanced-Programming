import java.sql.*;

public class Database {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String user = "student";
    private static final String password = "STUDENT";
    private Connection conn = null;
    private static Database instance;

    private Database() {
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Stabilesc conexiunea...");
            this.conn = DriverManager.getConnection(DB_URL, user, password);
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        } else if (instance.getConnection().isClosed()) {
            instance = new Database();
        }

        return instance;
    }
}