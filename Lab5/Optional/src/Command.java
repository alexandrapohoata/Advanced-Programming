import java.io.IOException;

public interface Command {
    void executeCommand() throws IOException;
    void help();
}
