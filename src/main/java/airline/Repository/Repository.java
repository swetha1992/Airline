package airline.Repository;

import airline.model.Flight;
import airline.model.TravelClass;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;

/**
 * Functions of the class parses over the corresponding JSON files to build List/Maps.
 * Assuming that the List/Map at the end is retrived from a DB.
 *
 * Note : Locations and TravelClass are considered in terms for Key-value and
 * enitre app considers the key for processing.
 *
 * Reason - Avoiding hardcodes.
 *          There might be situations at later stages when user wants to name business class as privelege class,
 *          on those cases it easy to change the value alone in DB and we need not touch the code as we process data on with IDs.
 *
 */
public class Repository {
    /**
     * Function Parses over the JSON file to build a list of all the flights/
     * @return List
     */
    public List<Flight> getFlights() {
        List<Flight> flightList = new ArrayList<Flight>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray flights = (JSONArray) parser.parse(new FileReader("src\\main\\resources\\Input Files\\FlightDetails.json"));
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
    public Map<String,String> getFlightClass() {
        Map<String,String> classMap = new LinkedHashMap<String,String>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray flightClasses = (JSONArray) parser.parse(new FileReader("src\\main\\resources\\Input Files\\TravelClass.json"));
            for (Object obj : flightClasses) {
                JSONObject flightClass = (JSONObject) obj;
                classMap.put((String) flightClass.get("id"),(String) flightClass.get("class"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classMap;
    }
}
