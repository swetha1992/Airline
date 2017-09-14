package airline.service;

import airline.model.Flight;
import airline.model.SearchCriteria;
import airline.model.TravelClass;
import airline.utility.DateUtility;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class SearchServiceTest {
    DateUtility dateUtility;

    @Before
    public void setUp() {

        List<Flight> flightList = new ArrayList<Flight>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray flights = (JSONArray) parser.parse(new FileReader("src\\main\\resources\\Input Files\\FlightDetailsTestData.json"));
            for (Object obj : flights) {
                JSONObject flight = (JSONObject) obj;
                List<String> flyingDays=new ArrayList<String>();
                JSONArray daysArray=(JSONArray)flight.get("Flying_days");
                for(Object day : daysArray){
                    flyingDays.add(day.toString().toUpperCase());
                }
                List<TravelClass> travelClasses=new ArrayList<TravelClass>();
                JSONArray travelClassArray=(JSONArray)flight.get("travel_class");
                for(Object travelClassObj : travelClassArray){
                    JSONObject travelClass = (JSONObject) travelClassObj;
                    TravelClass t=new TravelClass((String)travelClass.get("class"),
                            Integer.parseInt((String)travelClass.get("total_seats")),
                            Integer.parseInt((String)travelClass.get("available_seats")),
                            Double.parseDouble((String)travelClass.get("base_price")));
                    travelClasses.add(t);
                }
                Flight flightObj = new Flight((String) flight.get("flight_name"),
                        (String) flight.get("from"),
                        (String) flight.get("to"),
                        flyingDays,travelClasses);
                flightList.add(flightObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnTwoFlightsWhenMatchingFlightIsAvailable() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setPassengerCount(1);
        searchCriteria.setFlightClass("E");
        SearchService searchService = new SearchService();
        Assert.assertEquals(2, searchService.getsearchResult(searchCriteria).size());
    }

    @Test
    public void shouldReturnZeroFlightsWhenMatchingFlightAreNotAvailable() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("del");
        searchCriteria.setDestination("maa");
        searchCriteria.setPassengerCount(1);
        searchCriteria.setFlightClass("E");
        SearchService searchService = new SearchService();
        Assert.assertEquals(0, searchService.getsearchResult(searchCriteria).size());
    }
}