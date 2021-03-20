import java.io.IOException;
import java.util.List;

public class SaveCommand implements Command {
    Catalog cat;

    public SaveCommand(Catalog cat) {
        this.cat = cat;
    }

    @Override
    public void executeCommand() throws IOException {
        for (Item item : cat.getItemList()) {
            cat.save(item, "");
        }
    }

    @Override
    public void help() {
        System.out.println("Comanda save nu contine niciun parametru si salveaza fiecare item din catalog la o noua cale.");
    }
}

