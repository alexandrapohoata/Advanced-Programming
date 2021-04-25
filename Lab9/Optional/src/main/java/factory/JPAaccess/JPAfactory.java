package factory.JPAaccess;

import factory.Implementation;
import factory.MoviesDAO;

public class JPAfactory extends Implementation {
    @Override
    public MoviesDAO getMoviesDAO() {
        return new JPAmovies();
    }
}
