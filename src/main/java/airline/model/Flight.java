package airline.model;
import java.util.ArrayList;
import java.util.List;

/**
 * Flight Class contains flight name,from,to, String array of days on which the flights fly
 * (Assumed this coz, when ever the application is run we can check for the results instead of updating the repository
 * every time.It also solves the problem of removing past dates),List of TravelClass
 * (Modeled it this way coz, as per our business, each travel class ( E,F and B) has the following attribute
 * - base price, total seats, available seats. So, they can be made as model/class and since each flight
 * has atleast one of the travelclass, we can have list of this TravelClass object inside the flight class.
 */
public class Flight {
    private String flightName;
    private String to;
    private String from;
    private List<String> flyingDays =new ArrayList<String>();
    public List<String> getFlyingDays() {
        return flyingDays;
    }
    private List<TravelClass> travelClass =new ArrayList<TravelClass>();

    public Flight(String flightName, String from, String to, List<String> flyingDays, List<TravelClass> travelClass) {
        this.flightName = flightName;
        this.to = to;
        this.from = from;
        this.flyingDays = flyingDays;
        this.travelClass = travelClass;
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
    public List<TravelClass> getTravelClass() {
        return travelClass;
    }

}
