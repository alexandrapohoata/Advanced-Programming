package factory.JDBCaccess;

import controllers.MovieController;
import entity.MoviesEntity;
import factory.MoviesDAO;
import util.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCmovies extends MoviesDAO {
    @Override
    public void create(MoviesEntity e) throws SQLException {
        Database db = Database.getInstance();
        try (Connection connection = db.getConnection()) {
            MovieController movieController = new MovieController(connection);
            movieController.create(e.getId(), e.getTitle(), e.getReleaseDate(), e.getDuration(), e.getScore());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public MoviesEntity findById(String id) throws SQLException {
        Database db = Database.getInstance();
        MoviesEntity moviesEntity = new MoviesEntity();
        try (Connection connection = db.getConnection()) {
            MovieController movieController = new MovieController(connection);
            moviesEntity.setTitle(movieController.findById(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return moviesEntity;
    }

    @Override
    public List<MoviesEntity> findByName(String name) {
        return null;
    }
}
