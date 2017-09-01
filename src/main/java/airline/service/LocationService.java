package airline.service;
import airline.model.Location;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LocationService {
    public List<Location> getLocations() {
        List<Location> locationList = new ArrayList<Location>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray locations = (JSONArray) parser.parse(new FileReader("src\\main\\resources\\Input Files\\LocationMaster.json"));
            for (Object obj : locations) {
                JSONObject location = (JSONObject) obj;
                Location locationObj = new Location();
                locationObj.id = (String) location.get("id");
                locationObj.name = (String) location.get("name");
                locationList.add(locationObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationList;
    }
}
