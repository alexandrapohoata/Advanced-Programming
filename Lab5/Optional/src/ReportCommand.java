import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class ReportCommand implements Command {
    Catalog cat;

    public ReportCommand(Catalog catalog) {
        this.cat = catalog;
    }

    @Override
    public void executeCommand() throws IOException {
        for (Item item : cat.getItemList()) {
            PrintStream report = new PrintStream("report" + item.getName() + ".html");
            report.println("Class: " + item.getClass() + "| Author: " + item.getAuthor() + "| Release year: " + item.getReleaseYear());
        }
    }

    @Override
    public void help() {
        System.out.println("Comanda nu contine parametrii. Va creea un HTML report in directorul curent despre catalog.");
    }
}

