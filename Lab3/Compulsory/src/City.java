import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
}
