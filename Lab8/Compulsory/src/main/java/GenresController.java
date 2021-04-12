import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenresController {

    private final Connection connection;
    private static Statement stmt = null;
    private static int ID = 0;

    public GenresController(Connection connection) {
        this.connection = connection;
    }

    public void create(String name, int movie_id) {
        try {
            stmt = connection.createStatement();
            String sql = "insert into genres(id, name, movie_id) values ('" + ID++ + "', '" + name + "', '" + movie_id + "')";
            stmt.executeQuery(sql);
            System.out.println(name + " a fost adaugat in baza de date genres.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String findById(int genreID) throws SQLException {
        String name = null;
        stmt = connection.createStatement();
        String sql = "select name from genres where id = '" + genreID + "'";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next())
            name = rs.getString("name");

        return name;
    }
}
