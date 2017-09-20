package airline.service;

import airline.model.City;
import airline.model.Flight;
import airline.model.SearchCriteria;
import airline.model.TravelClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SearchService.class)
public class PriceServiceTest {

    Flight flight;
    @Before
    public void setUp() {
        City from =new City("HYD","HYDERABAD" );
        City to =new City("MAA","CHENNAI" );
        List<TravelClass> travelClasses=new ArrayList<TravelClass>();
        TravelClass e=new TravelClass("E",100, 50, 5000.0);
        TravelClass f=new TravelClass("F",100, 50, 5000.0);
        TravelClass b=new TravelClass("B",100, 50, 5000.0);
        travelClasses.add(e);
        travelClasses.add(f);
        travelClasses.add(b);
        List<String> flyingDays=new ArrayList<String>();
        flyingDays.add("Monday");
        flyingDays.add("Monday");
        flight = new Flight("A1",from,to,
                flyingDays,travelClasses);

    }


    @Test
    public void shouldReturnPriceForEconomy() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setPassengerCount(1);
        searchCriteria.setFlightClass("E");
        PriceService priceService = new PriceService();
        double actual = priceService.determineFareForEachFlight(flight,searchCriteria);
        Assert.assertEquals(6500.0,actual,0);
    }
    @Test
    public void shouldReturnPriceForFirst() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setPassengerCount(1);
        searchCriteria.setFlightClass("F");
        PriceService priceService = new PriceService();
        double actual = priceService.determineFareForEachFlight(flight,searchCriteria);
        Assert.assertEquals(5000.0,actual,0);
    }
    @Test
    public void shouldReturnPriceForBusiness() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setPassengerCount(1);
        searchCriteria.setFlightClass("B");
        PriceService priceService = new PriceService();
        double actual = priceService.determineFareForEachFlight(flight,searchCriteria);
        Assert.assertEquals(5000.0,actual,0);
    }
    @Test
    public void shouldReturnTotalPriceForEconomy() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setPassengerCount(2);
        searchCriteria.setFlightClass("E");
        PriceService priceService = new PriceService();
        double actual = priceService.determineFareForEachFlight(flight,searchCriteria);
        Assert.assertEquals(11500.0,actual,0);
    }
    @Test
    public void shouldReturnTotalPriceForFirst() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setPassengerCount(2);
        searchCriteria.setFlightClass("F");
        PriceService priceService = new PriceService();
        double actual = priceService.determineFareForEachFlight(flight,searchCriteria);
        Assert.assertEquals(10000.0,actual,0);
    }
    @Test
    public void shouldReturnTotalPriceForBusiness() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSource("hyd");
        searchCriteria.setDestination("maa");
        searchCriteria.setPassengerCount(2);
        searchCriteria.setFlightClass("B");
        PriceService priceService = new PriceService();
        double actual = priceService.determineFareForEachFlight(flight,searchCriteria);
        Assert.assertEquals(10000.0,actual,0);
    }
}