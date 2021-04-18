import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DirectorsMovie {
    private final Connection connection;
    private static Statement stmt = null;

    public DirectorsMovie(Connection connection) throws SQLException {
        this.connection = connection;
        stmt = connection.createStatement();
    }

    public void create(String name, String movie_id) {
        try {
            String sql = "insert into movie_directors(name, movie_id) values ('"
                    + name + "', '" + movie_id + "')";

            stmt.executeQuery(sql);
            System.out.println(name + " added to actors database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metoda ia regizorii unui film (filmul este identificat dupa un id al filmului)
    public String findById(String movie_id) throws SQLException {
        String sql;
        ResultSet rs;

        String results = "";


        sql = "select name from movie_directors where movie_id = '" + movie_id + "'";
        rs = stmt.executeQuery(sql);

        while (rs.next())
            results = rs.getString(1);      // 1 int

        return results;
    }
}
