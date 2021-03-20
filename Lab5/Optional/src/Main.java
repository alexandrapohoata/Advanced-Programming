import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Song song1 = new Song("song name1", "artist1", "1980");
        song1.setNrMinutes(4);
        song1.setViews(10000);
        song1.setPathName("C:\\Users\\Alexandra\\Desktop\\song1.mp3");

        Song song2 = new Song("song name2", "artist2", "1981");
        song2.setNrMinutes(3);
        song2.setViews(1000);
        song2.setPathName("C:\\Users\\Alexandra\\Desktop\\song2.mp3");

        Song song3 = new Song("song name3", "artist3", "1982");
        song3.setNrMinutes(3);
        song3.setViews(45000);
        song3.setPathName("C:\\Users\\Alexandra\\Desktop\\song3.mp3");

        Movie movie1 = new Movie("movie name1", "director1", "1990");
        movie1.setPathName("C:\\Users\\Alexandra\\Desktop\\movie1.mp4");
        movie1.setLength(90);

        Movie movie2 = new Movie("movie name2", "director2", "1991");
        movie2.setPathName("C:\\Users\\Alexandra\\Desktop\\movie2.mp4");
        movie2.setLength(100);

        Catalog catalog = new Catalog();
        catalog.add(song1);
        catalog.add(song2);
        catalog.add(song3);
        catalog.add(movie1);
        catalog.add(movie2);

        catalog.list();

        try {
            catalog.save(song1, "C:\\Users\\Alexandra\\Desktop\\songs\\song1.mp4");
            catalog.save(movie2, "C:\\Users\\Alexandra\\Desktop\\movies\\movie2.mp4");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("SHELL:");
        String comm;
        BufferedReader shell = new BufferedReader( new InputStreamReader(System.in));

        System.out.println("Bine ai venit! Ai intrat in shell. Comenzi disponibile:");
        System.out.println("\t list");
        System.out.println("\t add -type:song/movie -name:<nume> -author:<author> -path:<path> -releaseyear:<year>");
        System.out.println("\t play");
        System.out.println("\t report");
        System.out.println("\t quit");

        while (true) {
            System.out.print(" > ");
            comm = shell.readLine();

            AddCommand addCommand = new AddCommand(comm);
            if (addCommand.isValid()) {
                catalog.add(addCommand.getItem());
                System.out.println("Item-ul a fost adaugat cu succes in catalog.");
            } else if (comm.equals("quit")) {
                System.out.println("Closing shell");
                System.exit(0);
            } else if (comm.contains("list")) {
                ListCommand list = new ListCommand(catalog);

                if (comm.contains("help"))
                    list.help();
                else
                    list.executeCommand();
            } else if (comm.contains("save")) {
                SaveCommand save = new SaveCommand(catalog);

                if (comm.contains("help"))
                    save.help();
                else
                    save.executeCommand();
                System.out.println("Catalog was saved");
            } else if (comm.contains("play")) {
                PlayCommand playCommand = new PlayCommand(catalog);

                if (comm.contains("help"))
                    playCommand.help();
                else
                    playCommand.executeCommand();
                System.out.println("Catalog is playing audio items.");
            } else if (comm.contains("report")) {
                ReportCommand report = new ReportCommand(catalog);

                if (comm.contains("help"))
                    report.help();
                else
                    report.executeCommand();
                System.out.println("Catalog report created");
            }
        }
    }
}

