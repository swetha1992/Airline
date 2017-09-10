package airline.model;

/**
 * Class that contains data the user Inputs.
 */
public class SearchCriteria {
    private String source;
    private String destination;
    private int passengerCount;
    private String departureDate;
    private String flightClass;


    public String getDepartureDate() { return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate=departureDate; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) { this.destination = destination; }
    public int getPassengerCount() {
        return passengerCount;
    }
    public void setPassengerCount(int passengerCount) {
        this.passengerCount = (passengerCount==0 ? 1 : passengerCount);
    }
    public String getFlightClass() { return flightClass; }
    public void setFlightClass(String flightClass) { this.flightClass = flightClass; }
}
