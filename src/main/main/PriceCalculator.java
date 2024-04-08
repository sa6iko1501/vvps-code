package main;

import models.Train;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.List;

public class PriceCalculator {
    public List<Train> listOfTrains;

    public PriceCalculator(List<Train> listOfTrains) {
        this.listOfTrains = listOfTrains;
    }

    public List<Train> getTrains(){
        return listOfTrains;
    }

    public void addTrain(Train train){
        listOfTrains.add(train);
    }

    public void deleteTrain(Train train){
        listOfTrains.remove(train);
    }

    /**
     * Method which calculates the price based on the distance between two cities
     *
     * @param startingCity The city from which the train will depart
     * @param destinationCity The city in which the train will arrive
     * @return The price calculated based on the price per kilometer and the distance
     */
    public static double calculatePriceBasedOnDistance(String startingCity, String destinationCity){
        double pricePerKm = 0.35;
        /*
           We should already know the distance between the two cities before calling this method it should take
           the distance rather than the two cities...
           Let's just say the distance is 150km
         */
        double distance = 150;
        return (BigDecimal.valueOf(pricePerKm).multiply(BigDecimal.valueOf(distance)))
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Method which calculates the price based on the time in which the train ride takes place
     *
     * @param takeOff The time of leaving of the train
     * @param arrival The time of arrival at the end destination
     * @param priceBeforeDiscounts The price calculated by distance
     * @return The final price after discount based on time of day takes place, or the same price if the journey takes
     * place during peak hours
     */
    public static double calculatePriceBasedOnTimeOfJourney(LocalTime takeOff, LocalTime arrival,
                                                       double priceBeforeDiscounts){
        double priceAfterDiscount = priceBeforeDiscounts;
        //Check at which time of day the journey will take place
        if((takeOff.isAfter(LocalTime.of(9,30)) && arrival.isBefore(LocalTime.of(16,0))) ||
                (!takeOff.isAfter(LocalTime.of(19,30))))
        {
            priceAfterDiscount = (BigDecimal.valueOf(priceBeforeDiscounts)
                    .multiply(BigDecimal.valueOf(0.95))).setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        return priceAfterDiscount;
    }

    /**
     * Method which calculates the price based on if it's a one way ticket or a round trip
     *
     * @param isOneway true if the trip is one way
     * @param priceBeforeCalculation already calculated price
     * @return the final price
     */
    public static double calculatePriceBasedOnOneWayOrRoundTrip(boolean isOneway,double priceBeforeCalculation){
        double finalPrice = priceBeforeCalculation;
        if(!isOneway){
            finalPrice = (BigDecimal.valueOf(finalPrice).multiply(BigDecimal.TWO))
                    .setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        return finalPrice;
    }

    /**
     * Method which applies discounts to the price of the ticket
     *
     * @param priceBeforeDiscount price before any discounts are applied
     * @param hasSeniorCitizenDiscount true if client has a senior citizen discount
     * @param hasSmallChild true if client has a small child
     * @param hasFamilyCard true if client has a family card
     * @return the final price after all discounts the client is eligible for have been applied
     */
    public static double calculatePriceApplyDiscounts(double priceBeforeDiscount, boolean hasSeniorCitizenDiscount,
                                                      boolean hasSmallChild, boolean hasFamilyCard){
        double finalPrice = priceBeforeDiscount;
        if(hasSeniorCitizenDiscount){
            finalPrice = (BigDecimal.valueOf(finalPrice).multiply(BigDecimal.valueOf(0.66)))
                    .setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        if(hasSmallChild){
            if(hasFamilyCard){
                finalPrice = (BigDecimal.valueOf(finalPrice).multiply(BigDecimal.valueOf(0.5)))
                        .setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            else{
                finalPrice = (BigDecimal.valueOf(finalPrice).multiply((BigDecimal.valueOf(0.9))))
                        .setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        }
        return finalPrice;
    }
}
