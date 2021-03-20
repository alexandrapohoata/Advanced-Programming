public class Movie extends Item {
    private int length;

    public Movie() {
        super();
    }

    public Movie(String name, String author, String releaseYear) {
        super(name, author, releaseYear);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "length=" + length +
                '}';
    }
}

