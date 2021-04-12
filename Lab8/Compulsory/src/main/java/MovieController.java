import java.sql.*;

public class MovieController {
    private final Connection connection;
    private static Statement stmt = null;
    private static int ID = 0;

    public MovieController(Connection connection) {
        this.connection = connection;
    }

    public void create(String title, String release_date, String duration, String score) {
        try {
            stmt = connection.createStatement();
            String sql = "insert into movies(id, title, release_date, duration, score) values ('"
                    + ID++ + "', '" + title + "', '" + release_date + "', '" + duration + "', '" + score + "')";

            stmt.executeQuery(sql);
            System.out.println(title + " a fost adaugat in baza de date movies.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int findByName(String title) throws SQLException {
        int id = -1;
        stmt = connection.createStatement();
        String sql = "select id from movies where title = '" + title + "'";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next())
            id = rs.getInt(1);      // 1 int

        return id;
    }
}
