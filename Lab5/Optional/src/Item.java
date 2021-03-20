import java.io.Serializable;

public abstract class Item implements Serializable {
    private String name;
    private String author;
    private String releaseYear;
    private String pathName;

    public Item() {
        this.name = null;
        this.author = null;
        this.releaseYear = null;
        this.pathName = null;
    }

    public Item(String name, String author, String releaseYear) {
        this.name = name;
        this.author = author;
        this.releaseYear = releaseYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }
}

