import java.util.*;
import java.util.stream.Collectors;

public class City {
    private List<Location> nodes = new ArrayList<>();
    private String name;

    public void addLocation(Location node) {
        nodes.add(node);
    }
    public City(String name) {
        this.name = name;
    }
    public List<Location> getNodes() {
        return nodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Location l : nodes){
            for (Map.Entry<Location,Integer> entry : l.getCost().entrySet()){
                s.append(l.getName()).append("->");
                s.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
            }
        }
        return s.toString();
//        return "City\n{" +
//                "\nnodes=" + nodes +
//                ", name='" + name + '\'' +
//                "\n}\n";
    }

    public int getSize() {
        return nodes.size();
    }
    public void displayLocations() {
        List<Location> locations = nodes.stream().filter(location -> (location instanceof Visitable && !(location instanceof Payable))).collect(Collectors.toList());
        List<Visitable> visitables = new ArrayList<>();

        for(Location location : locations)
            visitables.add((Visitable) location);

        visitables.sort(Comparator.comparingInt(loc -> loc.getOpeningTime().toSecondOfDay()));

        System.out.println(visitables);
    }
}

