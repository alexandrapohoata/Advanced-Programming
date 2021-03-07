import java.time.LocalTime;

public class Museum extends Location implements Visitable, Payable {
    private LocalTime openingTime, closingTime;
    private double ticketPrice;

    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public LocalTime getOpeningTime() {
        return openingTime;
    }

    @Override
    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return this.getName() + " { " +
                "openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                ", ticketPrice=" + ticketPrice +
                "}";
    }
}
