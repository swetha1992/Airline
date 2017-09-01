package airline.service;

import airline.model.Flight;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SearchService {

    public List<Flight> searchFlight(String source, String destination) {
        List<Flight> flightList = new ArrayList<Flight>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray flights = (JSONArray) parser.parse(new FileReader("src\\main\\resources\\Input Files\\FlightDetails.json"));
            for (Object obj : flights) {
                JSONObject flight = (JSONObject) obj;
                if (flight.get("from").equals(source) && flight.get("to").equals(destination)) {
                    System.out.println("true condition");
                    Flight flightObj = new Flight();
                    flightObj.source = (String) flight.get("from");
                    flightObj.destination = (String) flight.get("to");
                    flightObj.flightName = (String) flight.get("flight_name");
                    flightList.add(flightObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flightList;
    }
}