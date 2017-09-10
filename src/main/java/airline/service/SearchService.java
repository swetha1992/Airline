package airline.service;

import airline.Repository.Repository;
import airline.model.Flight;
import airline.model.SearchCriteria;
import airline.model.TravelClass;
import airline.model.SearchResult;
import airline.utility.DateUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchService {
    /**
     * Function Filters the flights based on SearchCriteria.
     * @param searchCriteria
     * @return
     */
    public List<SearchResult> searchFlight(SearchCriteria searchCriteria) {
        List<SearchResult> searchResults = new ArrayList<SearchResult>();
        PriceService priceService = new PriceService();
        if (searchCriteria.getSource() != "" && searchCriteria.getDestination() != "") {
            Repository repository = new Repository();
            List<Flight> allFlights = repository.getFlights();
            List<Flight> availableFlights = new ArrayList<Flight>();
            DateUtility dateUtility = new DateUtility();
            //final int availableSeat = determineAvailableSeats(allFlights, searchCriteria);
            availableFlights = allFlights.stream()
                    .filter(flight -> flight.getFrom().equals(searchCriteria.getSource()))
                    .filter(flight -> flight.getTo().equals(searchCriteria.getDestination()))
                    .filter(flight -> determineAvailableSeats(flight,searchCriteria) >= searchCriteria.getPassengerCount())
                    .filter(flight -> (null == searchCriteria.getDepartureDate() ||
                            searchCriteria.getDepartureDate() == "" ||
                            (flight.getFlyingDays().contains(dateUtility.convertStringToLocalDate(searchCriteria.getDepartureDate())
                                    .getDayOfWeek().name()) ? Boolean.TRUE : Boolean.FALSE)))
                    .collect(Collectors.toList());
            searchResults = priceService.calculateFare(availableFlights, searchCriteria);
        }
        return searchResults;
    }

    /**
     * Loops over the flights and determine seats available based on travel class selected.
     * we need this function as travel class itself is a list inside the flights oject and hence
     * we need to loop over the list to get available seats.
     * @param flight
     * @param searchCriteria
     * @return
     */
    public int determineAvailableSeats(Flight flight,SearchCriteria searchCriteria) {
        int Seat = 0;
        List<TravelClass> travelClasses = flight.getTravelClass();
        for (TravelClass travelClass : travelClasses) {
            if (travelClass.getTravelClass().equals(searchCriteria.getFlightClass())) {
                Seat = travelClass.getAvailableSeats();
            }
        }
        return Seat;
    }
}




