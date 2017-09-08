package airline.Repository;

import airline.model.Flight;
import airline.utility.Utility;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightRepository {

    String ECONOMY_ID = "E";
    String FIRST_ID = "F";
    String BUSINESS_ID = "B";

    public List<Flight> getFlights() {
        List<Flight> flightList = new ArrayList<Flight>();
        JSONParser parser = new JSONParser();
        try {
            Utility utility=new Utility();
            JSONArray flights = (JSONArray) parser.parse(new FileReader("src\\main\\resources\\Input Files\\FlightDetails.json"));
            for (Object obj : flights) {
                JSONObject flight = (JSONObject) obj;

                Map<String,Integer> currentSeatAvailability=new HashMap<String, Integer>();
                currentSeatAvailability.put(ECONOMY_ID,Integer.parseInt((String)flight.get("available_economy_seats")));
                currentSeatAvailability.put(FIRST_ID,Integer.parseInt((String)flight.get("available_first_class_seats")));
                currentSeatAvailability.put(BUSINESS_ID,Integer.parseInt((String)flight.get("available_business_seats")));

                Map<String,Integer> totalSeatAvailability=new HashMap<String, Integer>();
                totalSeatAvailability.put(ECONOMY_ID,Integer.parseInt((String)flight.get("total_economy_seats")));
                totalSeatAvailability.put(FIRST_ID,Integer.parseInt((String)flight.get("total_first_class_seats")));
                totalSeatAvailability.put(BUSINESS_ID,Integer.parseInt((String)flight.get("total_business_seats")));

                Map<String,Integer> prices=new HashMap<String, Integer>();
                prices.put(ECONOMY_ID,Integer.parseInt((String)flight.get("economy_price")));
                prices.put(FIRST_ID,Integer.parseInt((String)flight.get("first_price")));
                prices.put(BUSINESS_ID,Integer.parseInt((String)flight.get("business_price")));

                Flight flightObj = new Flight((String) flight.get("flight_name"),
                                                (String) flight.get("to"),
                                                (String) flight.get("from"),
                                                utility.convertStringToLocalDate((String)flight.get("departure_date")),
                                                currentSeatAvailability,prices,totalSeatAvailability);
                flightList.add(flightObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flightList;
    }
}

