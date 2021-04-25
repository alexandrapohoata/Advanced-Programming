package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenreController {

    private final Connection connection;
    private static Statement stmt = null;

    public GenreController(Connection connection) throws SQLException {
        this.connection = connection;
        stmt = connection.createStatement();
    }

    public void create(String name, String movie_id) {
        try {
            String sql = "insert into genres(name, movie_id) values ('" + name + "', '" + movie_id + "')";
            stmt.executeQuery(sql);
            System.out.println(name + " added to genres database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // returnez genul filmului dupa id-ul filmului
    public String findById(String genreID) throws SQLException {
        String name = null;
        String sql = "select name from genres where id = '" + genreID + "'";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next())
            name = rs.getString("name");

        return name;
    }
}
