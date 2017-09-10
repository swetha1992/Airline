package airline.model;

/**
 * According to our stories, each travel class (E,F,B) has totalseats, available seats and base price.
 * Instead of having seperate maps that for each attribute ( E-6000.0, F-7000.0, B-8000.0 / E-100, F-50, B-10),
 * as assumed previously, we can can have a seperate class as travel class that maps all the three attributes to the corresponding
 * class.
 *
 * Each flight has atleast one of this class and hence each flight will have a list of typeClasses accordingly.
 * Also, when ever a new attribute associated with class is introduced , say beverages we can add up here.
 *
 */
public class TravelClass {
    String travelClass;
    int totalSeats;
    int availableSeats;
    double basePrice;

    public TravelClass(String travelClass, int totalSeats, int availableSeats, double basePrice) {
        this.travelClass = travelClass;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.basePrice = basePrice;
    }
    public String getTravelClass() { return travelClass; }
    public int getTotalSeats() {
        return totalSeats;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public double getBasePrice() {
        return basePrice;
    }
}
