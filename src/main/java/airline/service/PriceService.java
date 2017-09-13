package airline.service;

import airline.model.Flight;
import airline.model.SearchCriteria;
import airline.model.SearchResult;
import airline.model.TravelClass;
import airline.utility.ReadProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class to calculate price /
 */
public class PriceService {

    /**
     * Function loops over the final list of flights obtained after search and builts searchResult.
     *
     * @param flights
     * @param searchCriteria
     * @return
     */

    String ECONOMY_ID ="E";
    String FIRST_CLASS_ID ="F";
    String BUSINESS_ID ="B";
    int ECONOMY_PERCENTAGE_OF_SEATS_FOR_SURGE_PRICE_STAGE_1 = 50;
    int ECONOMY_PERCENTAGE_OF_SEATS_FOR_SURGE_PRICE_STAGE_2 = 90;
    int ECONOMY_PERCENTAGE_FOR_SURGE_PRICE_STAGE_1 = 30;
    int ECONOMY_PERCENTAGE_FOR_SURGE_PRICE_STAGE_2 = 60;
    String BUSINESS_FLYING_DAYS_MONDAY = "MONDAY";
    String BUSINESS_FLYING_DAYS_FRIDAY = "FRIDAY";
    String BUSINESS_FLYING_DAYS_SUNDAY = "SUNDAY";

    public List<SearchResult> calculateFare(List<Flight> flights, SearchCriteria searchCriteria) {
        List<SearchResult> searchResults = new ArrayList<SearchResult>();
        for (Flight flight : flights) {
            SearchResult searchResult = new SearchResult(flight.getFlightName(), flight.getFrom(),
                                        flight.getTo(), determineFareForEachFlight(flight, searchCriteria));
            searchResults.add(searchResult);
        }
        return searchResults;
    }

    /**
     * determines price for each flight.
     *
     * @param flight
     * @param searchCriteria
     * @return
     */
    public Double determineFareForEachFlight(Flight flight, SearchCriteria searchCriteria) {
        TravelClass travelClass = flight.getSelectedTravelClass(searchCriteria);
        double totalFare = 0, extraCost = 0.0;
        ReadProperties readProperties=new ReadProperties();
        if (travelClass.getTravelClass().equals(ECONOMY_ID)) {
            extraCost = extraCostForEconomy(travelClass.getPercentageOfSeatsFilled(),travelClass.getBasePrice());
        } else if (travelClass.getTravelClass().equals(FIRST_CLASS_ID)) {
            extraCost = extraCostForFirst();
        } else if (travelClass.getTravelClass().equals(BUSINESS_ID)) {
            extraCost = extraCostForBusiness(flight.getFlyingDays(), travelClass.getBasePrice());
        }
        totalFare = travelClass.getBasePrice() * searchCriteria.getPassengerCount() + extraCost;
        return totalFare;
    }

    public Double extraCostForEconomy(double percentageOfSeatsFilled,double basePrice ) {
        double extraCost = 0;
        if (percentageOfSeatsFilled >= ECONOMY_PERCENTAGE_OF_SEATS_FOR_SURGE_PRICE_STAGE_1
                                        && percentageOfSeatsFilled <= ECONOMY_PERCENTAGE_OF_SEATS_FOR_SURGE_PRICE_STAGE_2) {
            extraCost = (basePrice * ECONOMY_PERCENTAGE_FOR_SURGE_PRICE_STAGE_1) / 100;
        } else if (percentageOfSeatsFilled > ECONOMY_PERCENTAGE_OF_SEATS_FOR_SURGE_PRICE_STAGE_2) {
            extraCost = (basePrice * ECONOMY_PERCENTAGE_FOR_SURGE_PRICE_STAGE_2) / 100;
        } else {
            extraCost = 0.0;
        }
        return extraCost;
    }

    public Double extraCostForBusiness(List<String> days, double basePrice) {
        double extraCost = 0;
        if (days.contains(BUSINESS_FLYING_DAYS_MONDAY) ||
                days.contains(BUSINESS_FLYING_DAYS_FRIDAY) ||
                days.contains(BUSINESS_FLYING_DAYS_SUNDAY)) {
            extraCost = basePrice * 40 / 100;
        }
        return extraCost;
    }

    public Double extraCostForFirst() {
        double extraCost = 0;
        return extraCost;
    }
}