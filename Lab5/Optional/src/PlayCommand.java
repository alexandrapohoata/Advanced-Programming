import java.io.IOException;

public class PlayCommand implements Command {
    Catalog cat;

    public PlayCommand(Catalog cat) {
        this.cat = cat;
    }

    @Override
    public void executeCommand() throws IOException {
        for (Item item : cat.getItemList())
            cat.play(item);
    }

    @Override
    public void help() {
        System.out.println("Comanda play va da play la toate itemele din toate cataloagele.");
    }
}

