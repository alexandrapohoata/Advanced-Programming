import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            // creez conexiunea
            Database db = Database.getInstance();
            Connection connection = db.getConnection();

            // initializez controllere-le
            MovieController movieController = new MovieController(connection);
            GenreController genreController = new GenreController(connection);
            ActorsMovie actorsMovie = new ActorsMovie(connection);
            DirectorsMovie directorsMovie = new DirectorsMovie(connection);


            /* Pe prima linie a fisierului se afla coloanele bazei de date:

            imdb_title_id   // 0
            title           // 1
            original_title
            year
            date_published  // 4
            genre           // 5
            duration        // 6
            country
            language
            director        // 9
            writer
            production_company
            actors          // 12
            description
            avg_vote        // 14
            votes
            budget
            usa_gross_income
            worlwide_gross_income
            metascore
            reviews_from_users
            reviews_from_critics
             */

            // trebuie rulat o singura data inserarea in baza de date
            // din cauza ca la un moment dat vor aparea erori de genul "unique constraint violated"
            // nu pot insera de doua ori in baza de date, asa ca am comentat partea de cod care insereaza

//            final String pathname = "C:\\Users\\Alexandra\\Desktop\\FACULTATE\\AN II\\SEM 2\\Java\\Laboratoare\\laborator 8\\Optional\\src\\main\\resources\\IMDB movies.csv";
//            //https://stackoverflow.com/questions/7800494/parse-csv-with-double-quote-in-some-cases
//            Pattern pattern = Pattern.compile("\\s*(\"[^\"]*\"|[^,]*)\\s*");

//            File file = new File(pathname);
//            Scanner scanner = new Scanner(file);
//
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                if (line.contains("imdb_"))
//                    continue;
//
//                //actorii se afla pe coloana 12 separati prin virgula
//                List<String> columns = new ArrayList<>();
//                Matcher matcher = pattern.matcher(line);
//                // pattern-ul returneaza dupa o coloana, o linie "", de aceea folosesc un bool ca sa
//                // nu o adaug in lista
//                boolean remove = false;
//
//                //indetific coloanele cu matcher
//                while (matcher.find()) {
//                    String column = matcher.group(1);
//                    if (!remove)
//                        columns.add(column);
//                    remove = !remove;
//                }
//
//                // adaug filmul in baza de date
//                movieController.create(columns.get(0), columns.get(1), columns.get(4), columns.get(6), columns.get(14));
//
//                // adaug genurile (dupa parsarea liniei, genurile unui film pot fi de forma "Drama, Romance" etc
//                // de aceea, fac split si le adaug separat in baza de date
//                if (columns.get(5).contains("\"")) {
//                    //caut genurile, daca are mai multe genuri un film, elimin ghilimelele si le adaug in tabel
//                    for (String genre : columns.get(5).replace("\"", "").split(", "))
//                        genreController.create(genre, columns.get(0));
//                }
//
//                // adaug actorii
//                if (columns.get(12).contains("\"")) {
//                    for (String actor : columns.get(12).replace("\"", "").split(", ")) {
//                        actorsMovie.create(actor, columns.get(0));
//                    }
//                }
//
//                // adaug regizorul
//                directorsMovie.create(columns.get(9), columns.get(0));
//            }

            // caut un film dupa nume
            final String name = "After";
            String index = movieController.findByName(name);

            // daca returneaza "", filmul nu exista
            if (index.equals(""))
                System.out.println("Nu am gasit niciun id pt numele " + name + " in baza de date.");
            else    // in caz contrar, afisez id-ul numelui
                System.out.println(name + " ID: " + index);


            // regizorii filmului cu id-ul:
            String id = "tt0002101";
            System.out.println("Regizorul filmului dupa id-ul " + id + ":");
            System.out.println(directorsMovie.findById(id));

            System.out.println("Actorii filmului dupa id-ul " + id + ":");
            for (String actor : actorsMovie.findById(id))
                System.out.println(actor);


//            ActorsMovie actorsMovie = new ActorsMovie(connection);
//            actorsMovie.create("Teodora", "Elena", 1);
//            actorsMovie.create("Teodora", "Elena", 2);
//            actorsMovie.create("Stefan", "Iosif", 1);
//            actorsMovie.create("Stefan", "Iosif", 2);
//
//            System.out.println("Actori:");
//            for (String names : actorsMovie.findById(2))
//                System.out.println(names);
//
//            DirectorsMovie directorsMovie = new DirectorsMovie(connection);
//            directorsMovie.create("Teodora", "Elena", 1);
//            directorsMovie.create("Teodora", "Elena", 2);
//            directorsMovie.create("Stefan", "Iosif", 1);
//            directorsMovie.create("Stefan", "Iosif", 2);
//
//            System.out.println("Regizorii:");
//            for (String names : directorsMovie.findById(2))
//                System.out.println(names);
//
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Goodbye!");
    }
}