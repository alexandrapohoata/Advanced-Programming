import java.util.List;

public class ListCommand implements Command {
    Catalog cat;

    public ListCommand(Catalog cat) {
        this.cat = cat;
    }

    @Override
    public void executeCommand() {
        cat.list();
    }

    @Override
    public void help() {
        System.out.println("Comanda list nu contine parametrii. Va afisa toate articolele din cataloage.");
    }
}

