package app;

import entity.MoviesEntity;
import factory.Implementation;
import factory.JDBCaccess.JDBCfactory;
import factory.JPAaccess.JPAfactory;
import factory.MoviesDAO;
import repositories.MovieRepo;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // https://dzone.com/articles/jpa-entitymanagerfactory-interface-with-example
        // creez clasa singleton EntityManagerFactory
        MoviesEntity moviesEntity = new MoviesEntity();
        moviesEntity.setId("19999112");
        moviesEntity.setReleaseDate("2021-10-10");
        moviesEntity.setDuration("1h30m");
        moviesEntity.setScore("10.0");
        moviesEntity.setTitle("My title movie");

        MovieRepo repo = new MovieRepo();
        repo.create(moviesEntity);
        System.out.println("Filmul cu id-ul: 19999112: " + repo.findById("19999112").getTitle());

        callFactory();
    }

    public static void callFactory() {
        Implementation imp = new JDBCfactory();
        MoviesDAO moviesDAO = imp.getMoviesDAO();

        System.out.println("JDBC Factory implementation:");
        List<MoviesEntity> moviesEntities = moviesDAO.findByName("The");
        for (MoviesEntity m : moviesEntities)
            System.out.println(m.getId() + " " + m.getTitle() + " " + m.getReleaseDate());

        System.out.println("JPA Factory implementation:");
        imp = new JPAfactory();
        moviesDAO = imp.getMoviesDAO();
        moviesEntities = moviesDAO.findByName("The");
        for (MoviesEntity m : moviesEntities)
            System.out.println(m.getId() + " " + m.getTitle() + " " + m.getReleaseDate());
    }
}