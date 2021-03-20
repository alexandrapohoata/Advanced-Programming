import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create an object-oriented model of the problem. You should have at least the following classes:
 * Catalog and two item classes at your choice.
 * Consider using an interface or an abstract class in order to describe the items in a catalog.
 * Implement the following methods representing commands that will manage the content of the catalog:
 * add: adds a new entry into the catalog;s
 * list: prints the content of the catalog on the screen;
 * play: playback using the native operating system application (see the Desktop class);
 * save: saves the catalog to an external file (either as a text or binary, using object serialization);
 * load: loads the catalog from an external file.
 * The application will signal invalid data (year, path, etc.) using a custom exception.
 */

public class Catalog {
    //cream o lista de Item-uri
    private List<Item> itemList = new ArrayList<>();

    public List<Item> getItemList() {
        return itemList;
    }

    public void add(Item item) {
        itemList.add(item);
    }

    public void list() {
        System.out.println("Catalogul contine urmatoarele articole: ");
        itemList.forEach(item -> System.out.println(item.getName() +" author: " + item.getAuthor() + " pathName " + item.getPathName()));
    }

    public void play(Item item) throws IOException {
        System.out.println("Play song: " + item.getPathName());
        Desktop.getDesktop().open(new File(item.getPathName()));
    }

    public void save(Item item, String newPath) throws IOException
    {
        try (var oos = new ObjectOutputStream(new FileOutputStream(newPath)))
        {
            oos.writeObject(item);
            System.out.println("Succesfuly saved on " + newPath);
        }
    }

    public Catalog load(String path) throws IOException, ClassNotFoundException {

        Catalog catalog;
        try (var oos = new ObjectInputStream(new FileInputStream(path)))
        {
            catalog = (Catalog)oos.readObject();
            System.out.println("Succesfuly load from " + path);
        }
        return catalog;
    }
}

