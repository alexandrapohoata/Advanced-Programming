package factory;

import entity.MoviesEntity;

import java.sql.SQLException;
import java.util.List;

public abstract class MoviesDAO {
    public abstract void create(MoviesEntity e) throws SQLException;
    public abstract MoviesEntity findById(String id) throws SQLException;
    public abstract List<MoviesEntity> findByName(String name);
}