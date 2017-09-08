package airline.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Flight {
    private String flightName;
    private String to;
    private String from;
    private LocalDate departureDate;
    private Map<String, Integer> currentSeatAvailability = new HashMap<String, Integer>();
    private Map<String, Integer> totalSeatAvailability = new HashMap<String, Integer>();
    private Map<String, Integer> prices = new HashMap<String, Integer>();
    private double totalPrice;


    public Flight(String flightName, String to, String from, LocalDate departureDate, Map<String, Integer> flightClasses, Map<String, Integer> prices, Map<String, Integer> totalSeatAvailability) {
        this.flightName = flightName;
        this.to = to;
        this.from = from;
        this.departureDate = departureDate;
        this.currentSeatAvailability = flightClasses;
        this.prices = prices;
        this.totalSeatAvailability = totalSeatAvailability;
    }

    public String getTo() {
        return to;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getFrom() {
        return from;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public Map<String, Integer> getPrices() {
        return prices;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<String, Integer> getCurrentSeatAvailability() {
        return currentSeatAvailability;
    }
    public Map<String, Integer> getTotalSeatAvailability() {
        return totalSeatAvailability;
    }

}
