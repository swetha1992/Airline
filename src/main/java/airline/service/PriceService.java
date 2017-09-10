package airline.service;

import airline.model.Flight;
import airline.model.SearchCriteria;
import airline.model.SearchResult;
import airline.model.TravelClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class to calculate price /
 */
public class PriceService {

    /**
     * Function loops over the final list of flights obtained after search and builts searchResult.
     * @param flights
     * @param searchCriteria
     * @return
     */

    public List<SearchResult> calculateFare(List<Flight> flights, SearchCriteria searchCriteria) {
        List<SearchResult> searchResults = new ArrayList<SearchResult>();
        for (Flight flight : flights) {
            SearchResult searchResult = new SearchResult(flight.getFlightName(),
                    flight.getFrom(),
                    flight.getTo(),determinePrice(flight, searchCriteria));
            searchResults.add(searchResult);

        }
        System.out.println(searchResults);
        return searchResults;
    }

    /**
     * determines price for each flight.
     * @param flight
     * @param searchCriteria
     * @return
     */
    public Double determinePrice(Flight flight, SearchCriteria searchCriteria){
        List<TravelClass> travelClasses = flight.getTravelClass();
        double basePrice=0;
        for (TravelClass travelClass : travelClasses) {
            if (travelClass.getTravelClass().equals(searchCriteria.getFlightClass())) {
                basePrice = travelClass.getBasePrice();
            }
        }
        int passengerCount = searchCriteria.getPassengerCount();
        double totalFare = (double)(basePrice*passengerCount);
        return totalFare;
    }

}
