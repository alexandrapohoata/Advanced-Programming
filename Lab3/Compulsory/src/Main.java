import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] arggs) {
        Museum museum1 = new Museum();
        museum1.setOpeningTime(LocalTime.of(9, 30));
        museum1.setClosingTime(LocalTime.parse("17:00"));
        museum1.setTicketPrice(20);
        museum1.setName("Museum A");

        Church church1 = new Church();
        church1.setOpeningTime(LocalTime.of(7, 0));
        church1.setClosingTime(LocalTime.MIDNIGHT);
        church1.setName("Church A");

        Hotel hotel1 = new Hotel();
        hotel1.setName("Hotel");

        Museum museum2 = new Museum();
        museum2.setName("Muesum B");
        museum2.setOpeningTime(LocalTime.parse("10:00"));
        museum2.setClosingTime(LocalTime.parse("16:30"));
        museum2.setTicketPrice(15);

        Church church2 = new Church();
        church2.setOpeningTime(LocalTime.parse("07:30"));
        church2.setClosingTime(LocalTime.NOON);
        church2.setName("Church B");

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Restaurant");

        //Creez un obiect de tipul City unde adaug locatiile de mai sus
        City city = new City("Iasi");
        city.addLocation(museum1);
        city.addLocation(church1);
        city.addLocation(hotel1);
        city.addLocation(museum2);
        city.addLocation(church2);
        city.addLocation(restaurant1);

        //am un array de locatii Visitable
        Visitable[] arr = {museum1, church1, church2, museum2};
        System.out.println(Arrays.toString(arr)); //afisez locatiile

        //Intervalul de timp (cost) de la o locatile la alta
        Map<Location, Integer> cost = new HashMap<>();
        cost.put(museum1, 10);
        cost.put(museum2, 50);
        hotel1.setCost(cost);

        cost = new HashMap<>();
        cost.put(museum2, 20);
        cost.put(church1, 20);
        cost.put(church2, 10);
        museum1.setCost(cost);

        cost = new HashMap<>();
        cost.put(church1, 20);
        museum2.setCost(cost);

        cost = new HashMap<>();
        cost.put(church2, 30);
        cost.put(restaurant1, 10);
        church1.setCost(cost);

        cost = new HashMap<>();
        cost.put(restaurant1, 20);
        church2.setCost(cost);

        System.out.println(city);

    }
}
