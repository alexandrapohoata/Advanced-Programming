import java.util.Arrays;

/**
 * Clasa Factory extinde clasa Source
 */
public class Factory extends Source {
    /**
     * @param name Constructorul seteaza numele sursei
     */
    Factory(String name){
        this.name = name;
    }

    /**
     *
     * @return returneaza destinatiile din aceasta sursa
     */
    @Override
    public Destination[] getDestinations() {
        return this.destinations;
    }

    /**
     *
     * @return returneaza numele sursei
     */
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
        if (obj == null || !(obj instanceof Factory)) {
            return false;
        }
        Factory other = (Factory) obj;
        return this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return "Factory{" +
                "name='" + name + '\'' +
                ", destinations=" + Arrays.toString(destinations) +
                "}\n";
    }
}
