package airline.repository;

import airline.Repository.Repository;
import airline.model.City;
import airline.model.Flight;
import airline.model.TravelClassFares;
import airline.model.TravelClassSeats;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockRepository {

    public List<Flight> getMockFlights() {
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
                List<TravelClassSeats> travelClassesSeatsList=new ArrayList<TravelClassSeats>();
                List<TravelClassFares> travelClassesFareList=new ArrayList<TravelClassFares>();
                JSONArray travelClassArray=(JSONArray)flight.get("travel_class");
                for(Object travelClassObj : travelClassArray){
                    JSONObject travelClass = (JSONObject) travelClassObj;
                    TravelClassSeats seats=new TravelClassSeats((String)travelClass.get("class"),
                            Integer.parseInt((String)travelClass.get("total_seats")),
                            Integer.parseInt((String)travelClass.get("available_seats")));
                    TravelClassFares fares=new TravelClassFares((String)travelClass.get("class"),
                            Double.parseDouble((String)travelClass.get("base_price")));
                    travelClassesSeatsList.add(seats);
                    travelClassesFareList.add(fares);
                }
                City to =new City((String) flight.get("to"), getLocations().get((String)flight.get("to")));
                City from =new City((String) flight.get("from"),getLocations().get((String)flight.get("from")));
                Flight flightObj = new Flight((String) flight.get("flight_name"),from,to,
                        flyingDays,travelClassesSeatsList,travelClassesFareList);
                flightList.add(flightObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flightList;
    }
    /**
     * Function Parses over the JSON file to build a map of all the locations and its corresponding ID.
     * @return List
     */
    public Map<String,String> getLocations() {
        Map<String,String> locationMap = new HashMap<String,String>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray locations = (JSONArray) parser.parse(new FileReader("src\\main\\resources\\Input Files\\LocationMaster.json"));
            for (Object obj : locations) {
                JSONObject location = (JSONObject) obj;
                locationMap.put((String) location.get("id"),(String) location.get("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationMap;
    }
}
