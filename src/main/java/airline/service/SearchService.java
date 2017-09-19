package airline.service;

import airline.Repository.Repository;
import airline.model.Flight;
import airline.model.SearchCriteria;
import airline.model.SearchResult;
import airline.utility.DateUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    /**
     * Function Filters the flights based on SearchCriteria.
     * @param searchCriteria
     * @return
     */
    public List<SearchResult> getsearchResult(SearchCriteria searchCriteria) {
        List<SearchResult> searchResults = new ArrayList<SearchResult>();
        PriceService priceService = new PriceService();
        if (searchCriteria.getSource() != "" && searchCriteria.getDestination() != "") {
            searchResults = priceService.calculateFare(filerFlights(searchCriteria), searchCriteria);
        }
        return searchResults;
    }
    /**
     *
     * @param searchCriteria
     * @return
     */
    public List<Flight> filerFlights(SearchCriteria searchCriteria){
        Repository repository = new Repository();
        List<Flight> allFlights = repository.getFlights();
        DateUtility dateUtility = new DateUtility();
        return allFlights.stream()
                .filter(flight -> flight.getFrom().getCityId().equals(searchCriteria.getSource()))
                .filter(flight -> flight.getTo().getCityId().equals(searchCriteria.getDestination()))
                .filter(flight -> flight.getSelectedTravelClass(searchCriteria).getAvailableSeats() >=
                                        searchCriteria.getPassengerCount())
                .filter(flight -> (null == searchCriteria.getDepartureDate() ||
                                searchCriteria.getDepartureDate() == "" ||
                                (flight.getFlyingDays().contains(dateUtility
                                .convertStringToLocalDate(searchCriteria.getDepartureDate())
                                .getDayOfWeek().name()) ? Boolean.TRUE : Boolean.FALSE)))
                .collect(Collectors.toList());
    }
}




