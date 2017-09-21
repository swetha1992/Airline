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
    private City to;
    private City from;
    private List<String> flyingDays =new ArrayList<String>();
    private List<TravelClassSeats> travelClassSeats =new ArrayList<TravelClassSeats>();
    private List<TravelClassFares> travelClassFares =new ArrayList<TravelClassFares>();

    public Flight(String flightName, City from, City to, List<String> flyingDays, List<TravelClassSeats> travelClassSeats,List<TravelClassFares> travelClassFares) {
        this.flightName = flightName;
        this.to = to;
        this.from = from;
        this.flyingDays = flyingDays;
        this.travelClassSeats = travelClassSeats;
        this.travelClassFares = travelClassFares;
    }

    public List<TravelClassFares> getTravelClassFares() {
        return travelClassFares;
    }

    public City getTo() {
        return to;
    }
    public String getFlightName() {
        return flightName;
    }
    public City getFrom() {
        return from;
    }
    public List<TravelClassSeats> getTravelClassSeats() {
        return travelClassSeats;
    }
    public List<String> getFlyingDays() {
        return flyingDays;
    }

    public TravelClassSeats getSelectedTravelClassSeats(SearchCriteria searchCriteria){
        return getTravelClassSeats().stream()
                .filter(travelClassSeats -> travelClassSeats.getTravelClass().
                        equals(searchCriteria.getFlightClass())).findAny().orElse(null);
    }

    public TravelClassFares getSelectedTravelClassFares(SearchCriteria searchCriteria){
        return getTravelClassFares().stream()
                .filter(travelClassFares -> travelClassFares.getTravelClass().
                        equals(searchCriteria.getFlightClass())).findAny().orElse(null);
    }
}
