import java.util.Arrays;

public abstract class Source {
    protected String name;
    protected Destination[] destinations;

    public abstract Destination[] getDestinations();

    public abstract String getName();

    public abstract void setName(String name);

    public abstract void setDestinations(Destination[] destinations);
}