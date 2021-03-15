public abstract class Item {
    private String name;
    private String pathName;

    public Item(String name, String pathName) {
        this.name = name;
        this.pathName = pathName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }
}
