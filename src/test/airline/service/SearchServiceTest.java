package airline.service;

import airline.model.Flight;
import airline.model.SearchCriteria;
import airline.model.TravelClass;
import airline.utility.DateUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SearchServiceTest {
    DateUtility dateUtility;

    @Before
    public void setUp() {
        dateUtility = new DateUtility();
    }

    @Test
    public void shouldReturnThreeFlightsWhenMatchingFlightIsAvailable() {
        List<TravelClass> travelClasses = new ArrayList<TravelClass>();
        List<String> flyingDays = new ArrayList<String>();
        flyingDays.add("");
        TravelClass t1 = new TravelClass("E", 0, 0, 0.0);
        TravelClass t2 = new TravelClass("F", 0, 0, 0.0);
        TravelClass t3 = new TravelClass("B", 0, 0, 0.0);
        travelClasses.add(t1);
        travelClasses.add(t2);
        travelClasses.add(t3);
        Flight flightObj = new Flight("", "HYD", "MAA", flyingDays, travelClasses);
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        SearchService searchService = new SearchService();
        Assert.assertEquals(3, searchService.searchFlight(searchCriteria).size());
    }

    @Test
    public void shouldReturnFlightsMatchingDay() {
        List<TravelClass> travelClasses = new ArrayList<TravelClass>();
        List<String> flyingDays = new ArrayList<String>();
        flyingDays.add("SUNDAY");
        TravelClass t1 = new TravelClass("E", 0, 0, 0.0);
        TravelClass t2 = new TravelClass("F", 0, 0, 0.0);
        TravelClass t3 = new TravelClass("B", 0, 0, 0.0);
        travelClasses.add(t1);
        travelClasses.add(t2);
        travelClasses.add(t3);
        Flight flightObj = new Flight("", "HYD", "MAA", flyingDays, travelClasses);
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setDepartureDate("2017-09-10");
        SearchService searchService = new SearchService();
        Assert.assertEquals(1, searchService.searchFlight(searchCriteria).size());
    }
    @Test
    public void shouldReturnFlightsWhenMatchingPassengerCountIsAvailable() {
        List<TravelClass> travelClasses = new ArrayList<TravelClass>();
        List<String> flyingDays = new ArrayList<String>();
        flyingDays.add("");
        TravelClass t1 = new TravelClass("E", 100, 0, 0.0);
        TravelClass t2 = new TravelClass("F", 50, 10, 0.0);
        TravelClass t3 = new TravelClass("B", 10, 5, 0.0);
        travelClasses.add(t1);
        travelClasses.add(t2);
        travelClasses.add(t3);
        Flight flightObj = new Flight("", "HYD", "MAA", flyingDays, travelClasses);
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setFlightClass("E");
        searchCriteria.setPassengerCount(7);
        SearchService searchService = new SearchService();
        Assert.assertEquals(2, searchService.searchFlight(searchCriteria).size());
    }
    @Test
    public void shouldReturnFlightsWhenMatchingPassengerCountIsAvailableForFirst() {
        List<TravelClass> travelClasses = new ArrayList<TravelClass>();
        List<String> flyingDays = new ArrayList<String>();
        flyingDays.add("");
        TravelClass t1 = new TravelClass("E", 100, 0, 0.0);
        TravelClass t2 = new TravelClass("F", 50, 10, 0.0);
        TravelClass t3 = new TravelClass("B", 10, 5, 0.0);
        travelClasses.add(t1);
        travelClasses.add(t2);
        travelClasses.add(t3);
        Flight flightObj = new Flight("", "HYD", "MAA", flyingDays, travelClasses);
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setFlightClass("F");
        searchCriteria.setPassengerCount(7);
        SearchService searchService = new SearchService();
        Assert.assertEquals(2, searchService.searchFlight(searchCriteria).size());
    }
    @Test
    public void shouldReturnFlightsWhenMatchingPassengerCountIsAvailableForBusiness() {
        List<TravelClass> travelClasses = new ArrayList<TravelClass>();
        List<String> flyingDays = new ArrayList<String>();
        flyingDays.add("");
        TravelClass t1 = new TravelClass("E", 100, 0, 0.0);
        TravelClass t2 = new TravelClass("F", 50, 10, 0.0);
        TravelClass t3 = new TravelClass("B", 10, 5, 0.0);
        travelClasses.add(t1);
        travelClasses.add(t2);
        travelClasses.add(t3);
        Flight flightObj = new Flight("", "HYD", "MAA", flyingDays, travelClasses);
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setFlightClass("B");
        searchCriteria.setPassengerCount(7);
        SearchService searchService = new SearchService();
        Assert.assertEquals(2, searchService.searchFlight(searchCriteria).size());
    }
    @Test
    public void shouldReturnFlightListWhenPassengerCountIsZero() {
        List<TravelClass> travelClasses = new ArrayList<TravelClass>();
        List<String> flyingDays = new ArrayList<String>();
        flyingDays.add("");
        TravelClass t1 = new TravelClass("E", 0, 0, 0.0);
        TravelClass t2 = new TravelClass("F", 0, 0, 0.0);
        TravelClass t3 = new TravelClass("B", 0, 0, 0.0);
        travelClasses.add(t1);
        travelClasses.add(t2);
        travelClasses.add(t3);
        Flight flightObj = new Flight("", "HYD", "MAA", flyingDays, travelClasses);
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        SearchService searchService = new SearchService();
        Assert.assertEquals(3, searchService.searchFlight(searchCriteria).size());
    }
}