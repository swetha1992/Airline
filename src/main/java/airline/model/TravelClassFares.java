package airline.model;

public class TravelClassFares {
    String travelClass;
    double baseFare;


    public TravelClassFares(String travelClass, double baseFare) {
        this.travelClass = travelClass;
        this.baseFare = baseFare;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public double getBaseFare() {
        return baseFare;
    }
}
