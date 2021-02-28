import java.util.Arrays;

public class Warehouse extends Source {
    Warehouse(String name){
        this.name = name;
    }
    @Override
    public Destination[] getDestinations() {
        return this.destinations;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDestinations(Destination[] destinations) {
        this.destinations = destinations;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Warehouse)) {
            return false;
        }
        Warehouse other = (Warehouse) obj;
        return this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "name='" + name + '\'' +
                ", destinations=" + Arrays.toString(destinations) +
                "}\n";
    }
}

