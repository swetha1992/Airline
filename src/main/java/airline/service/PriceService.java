package airline.service;

import airline.model.Flight;
import airline.model.SearchCriteria;
import airline.model.SearchResult;
import airline.model.TravelClass;
import airline.utility.DateUtility;
import airline.utility.ReadProperties;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    ReadProperties readProperties = new ReadProperties();

    public List<SearchResult> calculateFare(List<Flight> flights, SearchCriteria searchCriteria) {
        return flights.stream().map(flight ->
                new SearchResult(flight.getFlightName(), flight.getFrom().getCityName(), flight.getTo().getCityName(),
                        determineFareForEachFlight(flight, searchCriteria)))
                .collect(Collectors.toList());
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
        if (travelClass.getTravelClass().equals(readProperties.getProperty("ECONOMY_ID"))) {
            extraCost = extraCostForEconomy(travelClass.getPercentageOfSeatsFilled(), travelClass.getBasePrice());
        } else if (travelClass.getTravelClass().equals(readProperties.getProperty("FIRST_CLASS_ID"))) {
            extraCost = extraCostForFirst(searchCriteria, travelClass.getBasePrice());
        } else if (travelClass.getTravelClass().equals(readProperties.getProperty("BUSINESS_ID"))) {
            extraCost = extraCostForBusiness(flight.getFlyingDays(), travelClass.getBasePrice());
        }
        totalFare = travelClass.getBasePrice() * searchCriteria.getPassengerCount() + extraCost;
        return totalFare;
    }

    public Double extraCostForEconomy(double percentageOfSeatsFilled, double basePrice) {
        double extraCost = 0;
        if (percentageOfSeatsFilled >= Double.parseDouble(readProperties.getProperty("ECONOMY_PERCENTAGE_OF_SEATS_FOR_SURGE_PRICE_STAGE_1"))
                && percentageOfSeatsFilled <= Double.parseDouble(readProperties.getProperty("ECONOMY_PERCENTAGE_OF_SEATS_FOR_SURGE_PRICE_STAGE_2"))) {
            extraCost = (basePrice * Double.parseDouble(readProperties.getProperty("ECONOMY_PERCENTAGE_FOR_SURGE_PRICE_STAGE_1"))) / 100;
        } else if (percentageOfSeatsFilled > Double.parseDouble(readProperties.getProperty("ECONOMY_PERCENTAGE_OF_SEATS_FOR_SURGE_PRICE_STAGE_2"))) {
            extraCost = (basePrice * Double.parseDouble(readProperties.getProperty("ECONOMY_PERCENTAGE_FOR_SURGE_PRICE_STAGE_2"))) / 100;
        } else {
            extraCost = 0.0;
        }
        return extraCost;
    }

    public Double extraCostForBusiness(List<String> days, double basePrice) {
        double extraCost = 0;
        if (days.contains(readProperties.getProperty("BUSINESS_FLYING_DAYS_MONDAY")) ||
                days.contains(readProperties.getProperty("BUSINESS_FLYING_DAYS_FRIDAY")) ||
                days.contains(readProperties.getProperty("BUSINESS_FLYING_DAYS_SUNDAY"))) {
            extraCost = basePrice * Integer.parseInt(readProperties.getProperty("BUSINESS_CLASS_PERCENTAGE_INCREASE")) / 100;
        }
        return extraCost;
    }

    public Double extraCostForFirst(SearchCriteria searchCriteria, double basePrice) {
        double extraCost = 0;
        if (searchCriteria.getDepartureDate() != null && searchCriteria.getDepartureDate() != "") {
            DateUtility dateUtility = new DateUtility();
            LocalDate bookingDate = dateUtility.convertStringToLocalDate(searchCriteria.getDepartureDate());
            Period daysBetween = LocalDate.now().until(bookingDate);
            if (daysBetween.getDays() > 0 && daysBetween.getDays() <= Integer.parseInt(readProperties.getProperty("FIRST_CLASS_BOOKING_OPEN_DAYS")))
            {
                int surgePercentage = (Integer.parseInt(readProperties.getProperty("FIRST_CLASS_BOOKING_OPEN_DAYS"))
                        - daysBetween.getDays())
                        * Integer.parseInt(readProperties.getProperty("FIRST_CLASS_PERCENTAGE_INCREASE"));
                extraCost = (basePrice * surgePercentage) / 100;
            }
        }
        return extraCost;
    }
}