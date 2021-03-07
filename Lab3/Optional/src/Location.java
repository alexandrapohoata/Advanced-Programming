import java.util.HashMap;
import java.util.Map;

public abstract class Location implements Comparable<Location> {
    private String name;
    private Map<Location, Integer> cost = new HashMap<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(Map<Location, Integer> cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public Map<Location, Integer> getCost() {
        return cost;
    }

    @Override
    public int compareTo(Location o) {
        if (this.name == null)
            return -1;

        return this.name.compareTo(o.name);
    }
}

