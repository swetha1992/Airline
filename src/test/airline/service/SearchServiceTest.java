package airline.service;

import airline.Repository.Repository;
import airline.model.City;
import airline.model.Flight;
import airline.model.SearchCriteria;
import airline.model.TravelClass;
import airline.repository.MockRepository;
import airline.utility.DateUtility;
import com.sun.org.apache.regexp.internal.RE;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SearchService.class)
public class SearchServiceTest {


    @MockBean
    Repository repository;
    @Before
    public void setUp() {
        MockRepository mockRepository=new MockRepository();
        Mockito.when(repository.getFlights()).thenReturn(mockRepository.getMockFlights());
    }


    @Test
    public void shouldReturnZeroFlightsWhenMatchingFlightAreNotAvailable() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("del");
        searchCriteria.setDestination("maa");
        searchCriteria.setPassengerCount(1);
        searchCriteria.setFlightClass("E");
        SearchService searchService = new SearchService();
        Assert.assertEquals(2, searchService.getsearchResult(searchCriteria).size());
    }
    @Test
    public void shouldReturnTwoFlightsWhenMatchingFlightAreNotAvailable() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("blr");
        searchCriteria.setDestination("del");
        searchCriteria.setPassengerCount(1);
        searchCriteria.setFlightClass("E");
        SearchService searchService = new SearchService();
        Assert.assertEquals(0, searchService.getsearchResult(searchCriteria).size());
    }
    @Test
    public void shouldReturnFlightsWhenPassengerCountIsZero() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setPassengerCount(0);
        searchCriteria.setFlightClass("E");
        SearchService searchService = new SearchService();
        Assert.assertEquals(2, searchService.getsearchResult(searchCriteria).size());
    }
    @Test
    public void shouldReturnFlightsBasedOnDayOfTravel() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setPassengerCount(0);
        searchCriteria.setFlightClass("E");
        searchCriteria.setDepartureDate("2017-09-25");
        SearchService searchService = new SearchService();
        Assert.assertEquals(2, searchService.getsearchResult(searchCriteria).size());
    }
    @Test
    public void shouldReturnFlightsBasedOnAvailability() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setPassengerCount(10);
        searchCriteria.setFlightClass("F");
        SearchService searchService = new SearchService();
        Assert.assertEquals(2, searchService.getsearchResult(searchCriteria).size());
    }
}