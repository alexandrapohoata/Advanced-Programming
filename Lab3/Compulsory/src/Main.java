import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] arggs) {
        Museum v2 = new Museum();
        v2.setOpeningTime(LocalTime.of(9, 30));
        v2.setClosingTime(LocalTime.parse("17:00"));
        v2.setTicketPrice(20);
        v2.setName("Museum A");

        Church v4 = new Church();
        v4.setOpeningTime(LocalTime.of(7, 0));
        v4.setClosingTime(LocalTime.MIDNIGHT);
        v4.setName("Church A");

        Hotel v1 = new Hotel();
        v1.setName("Hotel");

        Museum v3 = new Museum();
        v3.setName("Muesum B");
        v3.setOpeningTime(LocalTime.parse("10:00"));
        v3.setClosingTime(LocalTime.parse("16:30"));
        v3.setTicketPrice(15);

        Church v5 = new Church();
        v5.setOpeningTime(LocalTime.parse("07:30"));
        v5.setClosingTime(LocalTime.NOON);
        v5.setName("Church B");

        Restaurant v6 = new Restaurant();
        v6.setName("Restaurant");

        //Creez un obiect de tipul City unde adaug locatiile de mai sus
        City city = new City("Iasi");
        city.addLocation(v2);
        city.addLocation(v4);
        city.addLocation(v1);
        city.addLocation(v3);
        city.addLocation(v5);
        city.addLocation(v6);

        //am un array de locatii Visitable
        Visitable[] arr = {v2, v4, v5, v3};
        System.out.println(Arrays.toString(arr)); //afisez locatiile

        //Intervalul de timp (cost) de la o locatile la alta
        Map<Location, Integer> cost = new HashMap<>();
        cost.put(v2, 10);
        cost.put(v3, 50);
        v1.setCost(cost);

        cost = new HashMap<>();
        cost.put(v3, 20);
        cost.put(v4, 20);
        cost.put(v5, 10);
        v2.setCost(cost);

        cost = new HashMap<>();
        cost.put(v4, 20);
        v3.setCost(cost);

        cost = new HashMap<>();
        cost.put(v5, 30);
        cost.put(v6, 10);
        v4.setCost(cost);

        cost = new HashMap<>();
        cost.put(v6, 20);
        v5.setCost(cost);

        System.out.println(city);

    }
}
