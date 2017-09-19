package airline.repository;

import airline.Repository.Repository;
import airline.model.City;
import airline.model.Flight;
import airline.model.TravelClass;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MockRepository {

    public List<Flight> getMockFlights() {
        List<Flight> mockFlights = new ArrayList<Flight>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray flights = (JSONArray) parser.parse(new FileReader("src\\main\\resources\\Input Files\\FlightDetailsTestData.json"));
            for (Object obj : flights) {
                JSONObject flight = (JSONObject) obj;
                List<String> flyingDays = new ArrayList<String>();
                JSONArray daysArray = (JSONArray) flight.get("Flying_days");
                for (Object day : daysArray) {
                    flyingDays.add(day.toString().toUpperCase());
                }
                List<TravelClass> travelClasses = new ArrayList<TravelClass>();
                JSONArray travelClassArray = (JSONArray) flight.get("travel_class");
                for (Object travelClassObj : travelClassArray) {
                    JSONObject travelClass = (JSONObject) travelClassObj;
                    TravelClass t = new TravelClass((String) travelClass.get("class"),
                            Integer.parseInt((String) travelClass.get("total_seats")),
                            Integer.parseInt((String) travelClass.get("available_seats")),
                            Double.parseDouble((String) travelClass.get("base_price")));
                    travelClasses.add(t);
                }
                Repository flightrepository = new Repository();
                City to = new City((String) flight.get("to"), flightrepository.getLocations().get((String) flight.get("to")));
                City from = new City((String) flight.get("from"), flightrepository.getLocations().get((String) flight.get("from")));
                Flight flightObj = new Flight((String) flight.get("flight_name"), from, to,
                        flyingDays, travelClasses);
                mockFlights.add(flightObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mockFlights;
    }
}
