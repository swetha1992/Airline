package airline.controller;
import airline.Repository.Repository;
import airline.model.SearchCriteria;
import airline.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * Controller class maps the url to view/html and according to rwquest calls the corresponding service method.
 */
@Controller
public class FlightController {

    @RequestMapping("/")
    public String homePage(@ModelAttribute(value="SearchCriteria")SearchCriteria SearchCriteria,Model model) {
        model.addAttribute("SearchCriteria",SearchCriteria);
        Repository repository=new Repository();
        repository.getFlights();
        return "flightSearch";
    }

    @RequestMapping(value = "/search", method=RequestMethod.POST)
    public String searchResult(@ModelAttribute(value="SearchCriteria")SearchCriteria searchCriteria, Model model) {
        SearchService searchService = new SearchService();
        model.addAttribute("availableFlights", searchService.getsearchResult(searchCriteria));
        return "flightSearch";
    }


    @ModelAttribute("locations")
    public Map<String,String> getLocations() {
        Repository repository=new Repository();
        return repository.getLocations();
    }
    @ModelAttribute("flightClasses")
    public Map<String,String> getFlightClasses() {
       Repository repository=new Repository();
        return repository.getFlightClass();
    }
}