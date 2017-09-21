package airline.service;

import airline.model.*;
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

        List<TravelClassSeats> travelClassesSeats=new ArrayList<TravelClassSeats>();
        TravelClassSeats e=new TravelClassSeats("E",100, 50);
        TravelClassSeats f=new TravelClassSeats("F",100, 50);
        TravelClassSeats b=new TravelClassSeats("B",100, 50);
        travelClassesSeats.add(e);
        travelClassesSeats.add(f);
        travelClassesSeats.add(b);

        List<TravelClassFares> travelClassFares=new ArrayList<TravelClassFares>();
        TravelClassFares economy=new TravelClassFares("E",4000.0);
        TravelClassFares first=new TravelClassFares("F",5000.0);
        TravelClassFares business=new TravelClassFares("B",6000.0);
        travelClassFares.add(economy);
        travelClassFares.add(first);
        travelClassFares.add(business);

        List<String> flyingDays=new ArrayList<String>();
        flyingDays.add("Monday");
        flyingDays.add("Monday");
        flight = new Flight("A1",from,to,
                flyingDays,travelClassesSeats,travelClassFares);

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
        Assert.assertEquals(5200.0,actual,0);
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
        Assert.assertEquals(6000.0,actual,0);
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
        Assert.assertEquals(9200.0,actual,0);
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
        Assert.assertEquals(12000.0,actual,0);
    }
}