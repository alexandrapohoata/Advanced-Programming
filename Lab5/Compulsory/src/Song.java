public class Song extends Item {
    private String author;
    private String releaseYear;
    private int nrMinutes;
    private int views;

    public Song(String name, String pathName) {
        super(name, pathName);
    }

    public int getNrMinutes() {
        return nrMinutes;
    }

    public void setNrMinutes(int nrMinutes) {
        this.nrMinutes = nrMinutes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "Song{" +
                "nrMinutes=" + nrMinutes +
                ", views=" + views +
                '}';
    }
}
