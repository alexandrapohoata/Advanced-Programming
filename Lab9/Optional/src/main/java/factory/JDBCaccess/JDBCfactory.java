package factory.JDBCaccess;

import factory.Implementation;
import factory.MoviesDAO;

public class JDBCfactory extends Implementation {

    @Override
    public MoviesDAO getMoviesDAO() {
        return new JDBCmovies();
    }
}