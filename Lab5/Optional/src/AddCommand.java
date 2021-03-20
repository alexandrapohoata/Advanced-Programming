import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddCommand extends Item implements Command {
    private List<String> arguments = new ArrayList<>();
    private Item item;
    private String type;
    private String name;
    private String author;
    private String releaseYear;
    private String pathName;
    private boolean valid;

    public AddCommand(String arguments) {
        String[] args = arguments.split(" ");
        this.valid = args[0].equals("add");
        this.arguments.addAll(Arrays.asList(args).subList(1, args.length));
    }

    public boolean isValid() {
        return valid;
    }

    public Item getItem() {
        if (type.equals("song")) {
            Song song = new Song(name, author, releaseYear);
            song.setPathName(pathName);
            return song;
        } else if (type.equals("movie")) {
            Movie movie = new Movie(name, author, releaseYear);
            movie.setPathName(pathName);
        }
        return null;
    }

    @Override
    public void executeCommand() {
        for (int i = 0; i < this.arguments.size(); i++) {
            String arg = this.arguments.get(i);
            String argname = arg.split(":")[0];
            String value = arg.split(":")[1];

            switch (argname) {
                case "-type" -> type = value;
                case "-name" -> name = value;
                case "-path" -> pathName = value;
                case "-author" -> author = value;
                case "-releaseyear" -> releaseYear = value;
            }
        }
    }

    @Override
    public void help() {
        System.out.println("Foloseste comanda add cu urmtorii parametri (Nu conteaza ordinea lor):");
        System.out.println("-type:song/movie");
        System.out.println("-name:nume item");
        System.out.println("-path:path item");
        System.out.println("-author: author item");
        System.out.println("-releaseyear: release year item");
    }
}

