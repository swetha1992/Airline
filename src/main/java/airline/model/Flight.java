package airline.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Flight {
    private String flightName;
    private String to;
    private String from;
    private LocalDate departureDate;
    private Map<String, Integer> flightClasses = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> prices = new HashMap<String, Integer>();
    private int totalPrice;


    public Flight(String flightName, String to, String from, LocalDate departureDate, Map<String, Integer> flightClasses, Map<String, Integer> prices) {
        this.flightName = flightName;
        this.to = to;
        this.from = from;
        this.departureDate = departureDate;
        this.flightClasses = flightClasses;
        this.prices = prices;
    }
    public String getTo() {
        return to;
    }
    public String getFlightName() { return flightName; }
    public String getFrom() {
        return from;
    }
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public Map<String, Integer> getFlightClasses() { return flightClasses; }
    public Map<String, Integer> getPrices() { return prices; }
    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice;}
}
