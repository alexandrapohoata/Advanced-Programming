import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ActorsMovie {
    private final Connection connection;
    private static Statement stmt = null;

    public ActorsMovie(Connection connection) throws SQLException {
        this.connection = connection;
        stmt = connection.createStatement();
    }

    public void create(String name, String movie_id) {
        try {
            String sql = "insert into movie_actors(name, movie_id) values ('"
                    + name + "', '" + movie_id + "')";

            stmt.executeQuery(sql);
            System.out.println(name + " added to actors database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metoda ia actorii unui film (filmul este identificat dupa un id al filmului)
    public String[] findById(String movie_id) throws SQLException {
        String sql;
        ResultSet rs;

        sql = "select count(movie_id) from movie_actors where movie_id = '" + movie_id + "'";
        rs = stmt.executeQuery(sql);
        int total = rs.next() ? rs.getInt(1) : 0;

        String[] results = new String[total];
        int i = 0;

        sql = "select name from movie_actors where movie_id = '" + movie_id + "'";
        rs = stmt.executeQuery(sql);

        while (rs.next())
            results[i++] = rs.getString(1);      // 1 int

        return results;
    }
}
