package airline.model;
/**
 * Class that contains data displayed to user.
 */
public class SearchResult {
    private String flightName;
    private String source;
    private String destination;
    private double totalFare;

    public SearchResult(String flightName, String source, String destination, double totalFare) {
        this.flightName = flightName;
        this.source = source;
        this.destination = destination;
        this.totalFare = totalFare;
    }

    public String getFlightName() { return flightName; }
    public String getSource() {
        return source;
    }
    public String getDestination() {
        return destination;
    }
    public double getTotalFare() {
        return totalFare;
    }
}
