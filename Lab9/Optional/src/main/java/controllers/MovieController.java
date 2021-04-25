package controllers;

import java.sql.*;

public class MovieController {
    private final Connection connection;
    private static Statement stmt = null;

    public MovieController(Connection connection) throws SQLException {
        this.connection = connection;
        stmt = connection.createStatement();
    }

    public void create(String unique_id, String title, String release_date, String duration, String score) {
        try {
            String sql = "insert into movies(id, title, release_date, duration, score) values ('"
                    + unique_id + "', '" + title + "', '" + release_date + "', '" + duration + "', '" + score + "')";

            stmt.executeQuery(sql);
            System.out.println(title + " added to movies database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String findByName(String title) throws SQLException {
        String id = "";
        String sql = "select id from movies where title = '" + title + "'";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next())
            id = rs.getString(1);      // 1 int

        return id;
    }

    public String findById(String id) throws SQLException {
        String title = "";
        String sql = "select title from movies where id = '" + id + "'";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next())
            title = rs.getString(1);      // 1 int

        return title;
    }
}
