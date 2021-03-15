import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Song song1 = new Song("song name1", "C:\\Users\\Alexandra\\Desktop\\song1.mp3");
        song1.setNrMinutes(4);
        song1.setViews(10000);

        Song song2 = new Song("song name2", "C:\\Users\\Alexandra\\Desktop\\song2.mp3");
        song2.setNrMinutes(3);
        song2.setViews(1000);

        Song song3 = new Song("song name3", "C:\\Users\\Alexandra\\Desktop\\song3.mp3");
        song3.setNrMinutes(3);
        song3.setViews(45000);

        Movie movie1 = new Movie("movie name1", "C:\\Users\\Alexandra\\Desktop\\movie1.mp4");
        movie1.setLength(90);

        Movie movie2 = new Movie("movie name2", "C:\\Users\\Alexandra\\Desktop\\movie2.mp4");
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

    }
}
