package airline.model;

import airline.service.SearchService;
import airline.utility.Utility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlightTest {
    Utility utility;
    @Before
    public void setUp() {
        utility = new Utility();
    }
    @Test
    public void shouldReturnTrueWhenMatchingFlightIsAvailable(){
        Flight flightObj = new Flight("", "maa" ,"maa",null,null,null);
        SearchCriteria searchCriteria=new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        SearchService searchService=new SearchService();
        Assert.assertTrue(searchService.fromToMatch(flightObj,searchCriteria));
    }
    @Test
    public void shouldReturnFalseWhenMatchingFlightIsNotAvailable0(){
        Flight flightObj = new Flight("", "maa" ,"maa",null,null,null);
        SearchCriteria searchCriteria=new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        SearchService searchService=new SearchService();
        Assert.assertFalse(searchService.fromToMatch(flightObj,searchCriteria));
    }

    @Test
    public void shouldReturnTrueDepartureDateMatch(){
        Flight flightObj = new Flight("", "maa" ,"maa",null,null,null);
        SearchCriteria searchCriteria=new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        SearchService searchService=new SearchService();
        Assert.assertTrue(searchService.departureDateMatch(flightObj,searchCriteria));
    }
    @Test
    public void shouldReturnTrueIfDeparturDateIsEmpty(){
        Flight flightObj = new Flight("", "maa" ,"maa",null,null,null);
        SearchCriteria searchCriteria=new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        SearchService searchService=new SearchService();
        Assert.assertTrue(searchService.departureDateMatch(flightObj,searchCriteria));
    }
}