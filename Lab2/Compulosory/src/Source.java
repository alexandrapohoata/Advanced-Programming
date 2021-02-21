import java.util.Arrays;

public class Source {
    private String name;
    private SourceType sourceType;
    private Destination[] destinations;

    public Source(String name, SourceType sourceType, Destination[] dests) {
        this.name = name;
        this.sourceType = sourceType;
        this.destinations = dests;
    }

    public Destination[] getDestinations() {
        return destinations;
    }

    public String getName() {
        return name;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    @Override
    public String toString() {
        return "Source{" +
                "name='" + name + '\'' +
                ", sourceType=" + sourceType +
                ", destinations=" + Arrays.toString(destinations) +
                "}\n";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }
}

