import java.time.Duration;
import java.time.LocalTime;

public interface Visitable {
    LocalTime getOpeningTime();
    LocalTime getClosingTime();

    default LocalTime setDefaultOpeningTime() {
        return LocalTime.parse("09:30");
    }

    default LocalTime setDefaultClosingTime() {
        return LocalTime.parse("20:00");
    }

    static Duration getVisitingDuration(Location location) {
        Duration duration = null;
        return duration;
    }
}

