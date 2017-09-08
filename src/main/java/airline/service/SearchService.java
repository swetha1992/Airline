package airline.service;

import airline.Repository.FlightRepository;
import airline.model.Flight;
import airline.model.SearchCriteria;
import airline.utility.Utility;
import java.util.ArrayList;
import java.util.List;

public class SearchService {

    public List<Flight> searchFlight(SearchCriteria searchCriteria) {
        FlightRepository flightRepository=new FlightRepository();
        List<Flight> flightList = flightRepository.getFlights();
        List<Flight> availableFlightList = new ArrayList<Flight>();
        for (Flight flight : flightList){
            if(availability(flight,searchCriteria)){
                PriceService priceService=new PriceService();
                flight.setTotalPrice(priceService.determinePrice(flight,searchCriteria));
                availableFlightList.add(flight);
            }
        }
        return availableFlightList;
    }

    public Boolean availability(Flight flight, SearchCriteria searchCriteria){
        Boolean availability= (fromToMatch(flight,searchCriteria) &&
                seatAvailability(flight,searchCriteria) &&
                    departureDateMatch(flight,searchCriteria)? Boolean.TRUE : Boolean.FALSE);
        return availability;
    }
    public Boolean fromToMatch(Flight flight, SearchCriteria searchCriteria){
        Boolean isFromToMatch= flight.getFrom().equals(searchCriteria.getSource())
                && flight.getTo().equals(searchCriteria.getDestination());
        return isFromToMatch;
    }
    public Boolean seatAvailability(Flight flight, SearchCriteria searchCriteria){
        int seats=flight.getCurrentSeatAvailability().get(searchCriteria.getFlightClass());
        Boolean isSeatAvailable=(seats > searchCriteria.getPassengerCount() ? Boolean.TRUE : Boolean.FALSE);
        return isSeatAvailable;
    }
    public Boolean departureDateMatch(Flight flight, SearchCriteria searchCriteria){
        Utility utility=new Utility();
        Boolean dateAvailable=((null==searchCriteria.getDepartureDate() ||
                    searchCriteria.getDepartureDate() == ""||
                        flight.getDepartureDate().compareTo
                                (utility.convertStringToLocalDate(searchCriteria.getDepartureDate()))==0)
                                    ? Boolean.TRUE :Boolean.FALSE);
        return dateAvailable;
    }
}
