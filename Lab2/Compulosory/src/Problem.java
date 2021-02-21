import java.util.Arrays;

public class Problem {
    private final Source[] source;
    private Destination[] destination;

    public Problem(Source[] source, Destination[] destination) {
        this.source = source;
        this.destination = destination;
    }

    public Source[] getSource() {
        return source;
    }

    public void setSource(Source source, int index) {
        this.source[index] = source;
    }

    public Destination getDestination(int index) {
        return destination[index];
    }

    public void setDestination(Destination[] destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "source=" + Arrays.toString(source) +
                '}';
    }
}
