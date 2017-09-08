package airline.service;

import airline.model.Flight;
import airline.model.SearchCriteria;

public class PriceService {

    public Double determinePrice(Flight flight, SearchCriteria searchCriteria){
        int basePrice= flight.getPrices().get(searchCriteria.getFlightClass());
        int passengerCount = searchCriteria.getPassengerCount();
        double totalPrice = (double)(basePrice*passengerCount);
        return totalPrice;
    }

    public double determineExtraCost(Flight flight, SearchCriteria searchCriteria){
        double extraCost =0;
        int availableSeats=flight.getCurrentSeatAvailability().get(searchCriteria.getFlightClass());
        int totalSeats=flight.getTotalSeatAvailability().get(searchCriteria.getFlightClass());
        int basePrice= flight.getPrices().get(searchCriteria.getFlightClass());
        if(searchCriteria.getFlightClass()=="E"){
            calculateExtraCostForEconomy(availableSeats,totalSeats,basePrice);
        }else if(searchCriteria.getFlightClass()=="F"){
            calculateExtraCostForFirst();
        }else{
            calculateExtraCostForBusiness();
        }

        return extraCost;
    }
    public double calculateExtraCostForEconomy(int availableSeats,int totalSeats, int basePrice){
        double extraCost =0;
        double proportion = (((double) availableSeats) / ((double) totalSeats))*100;

        return extraCost;
    }
    public double calculateExtraCostForFirst(){
        double extraCost =0;
        return extraCost;
    }
    public double calculateExtraCostForBusiness(){
        double extraCost =0;
        return extraCost;
    }
}
