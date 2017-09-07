package airline.controller;

import airline.Repository.GeneralRepository;
import airline.model.SearchCriteria;
import airline.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * Created by rajashrk on 8/8/17.
 */
@Controller
public class FlightController {

    @RequestMapping("/")
    public String homePage(@ModelAttribute(value="SearchCriteria")SearchCriteria SearchCriteria,Model model) {
        model.addAttribute("SearchCriteria",SearchCriteria);
        return "flightSearch";
    }

    @RequestMapping(value = "/search", method=RequestMethod.POST)
    public String searchResult(@ModelAttribute(value="SearchCriteria")SearchCriteria searchCriteria, Model model) {
        SearchService searchService = new SearchService();
        model.addAttribute("availableFlights", searchService.searchFlight(searchCriteria));
        return "flightSearch";
    }
    @ModelAttribute("locations")
    public Map<String,String> getLocations() {
        GeneralRepository generalRepository=new GeneralRepository();
        return generalRepository.getLocations();
    }
    @ModelAttribute("flightClasses")
    public Map<String,String> getFlightClasses() {
        GeneralRepository generalRepository=new GeneralRepository();
        return generalRepository.getFlightClass();
    }
}