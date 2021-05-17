import java.io.IOException;

public interface Command {
    void execute(String param) throws IOException, ClassNotFoundException;
}
