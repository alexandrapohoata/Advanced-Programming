import java.util.Arrays;
import java.util.Map;

public class TravelPlan {
    private final City city;

    public TravelPlan(City city) {
        this.city = city;
    }

    public void getShortestPath() {
        Location[] locations = new Location[city.getSize()];
        int contor = 0;
        for (Location location : city.getNodes()) {
            int min = Integer.MAX_VALUE;
            for (Map.Entry<Location, Integer> neighbour : location.getCost().entrySet()) {
                Integer cost = neighbour.getValue();
                if (cost < min) {
                    locations[contor] = neighbour.getKey();
                }
            }
            contor++;
        }
        System.out.println("Shortest path = " + Arrays.toString(locations));
    }
}

