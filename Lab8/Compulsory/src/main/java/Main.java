import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // creez conexiunea
            connection = Database.getConnection();

            // inserez in baza de date movies urmatoarele
            MovieController movieController = new MovieController(connection);
            movieController.create("Solace", "2016-12-16", "1h41m", "6.4");
            movieController.create("After", "2019-04-12", "1h45m", "5.3");
            movieController.create("The silence of the lambs", "1991-02-14", "1h58m", "8.6");

            // caut un film dupa nume
            final String name = "After";
            int index = movieController.findByName(name);

            // daca returneaza -1, filmul nu exista
            if (index == -1)
                System.out.println("Nu am gasit niciun id pt numele " + name + " in baza de date.");
            else    // in caz contrar, afisez id-ul numelui
                System.out.println(name + " ID: " + index);

            // daca id-ul filmului de mai sus nu exista in baza de date genres,
            // introduc genul filmului
            GenresController genreController = new GenresController(connection);
            if (index != -1) {
                genreController.create("Drama", index);
                genreController.create("Romance", index);
            }

            System.out.println(name + " ID: " + genreController.findById(index));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null)
                Database.closeConnection();
        }
        System.out.println("Goodbye!");
    }
}
