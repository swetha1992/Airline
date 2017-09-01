package airline.controller;

import airline.service.LocationService;
import airline.service.SearchService;
import airline.model.Location;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by rajashrk on 8/8/17.
 */
@Controller
@SpringBootApplication
public class FlightController {

    public static void main(String[] args) {
        SpringApplication.run(FlightController.class, args);
    }

    @RequestMapping("/")
    public String homePage() {
        return "flightSearch";
    }

    @RequestMapping(value = "/search", method=RequestMethod.POST)
    public String searchResult(@RequestParam("source") String source, @RequestParam("destination") String destination, Model model) {
        SearchService searchService = new SearchService();
        model.addAttribute("availableFlights", searchService.searchFlight(source, destination));
        return "flightSearch";
    }
    @ModelAttribute("locations")
    public List<Location> getLocations() {
        LocationService locationService = new LocationService();
        return locationService.getLocations();
    }
}