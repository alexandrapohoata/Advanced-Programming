import java.time.LocalTime;

public class Church extends Location implements Visitable {
    private LocalTime openingTime, closingTime;

    @Override
    public LocalTime getOpeningTime() {
        return this.openingTime ;
    }

    @Override
    public LocalTime getClosingTime() {
        return this.closingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }
    @Override
    public String toString() {
        return this.getName() + " { " +
                "openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                "}";
    }
}
