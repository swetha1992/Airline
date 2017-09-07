package airline.Repository;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GeneralRepository {

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
            JSONArray flightClasses = (JSONArray) parser.parse(new FileReader("src\\main\\resources\\Input Files\\FlightClass.json"));
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
