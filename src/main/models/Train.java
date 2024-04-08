package models;

import java.time.LocalDateTime;

public class Train {
    private final LocalDateTime departure;
    private final LocalDateTime arrival;
    private final double distanceInKm;
    private final String departingCity;

    private final String destinationCity;

    public Train(LocalDateTime departure, LocalDateTime arrival, String departingCity, String destinationCity
            ,double distanceInKm) {
        if(departure == null || arrival == null || departingCity == null || destinationCity == null
                || departingCity.isEmpty() || destinationCity.isEmpty() || distanceInKm < 0){
            throw new RuntimeException("Invalid data for train");
        }
        if(departure.isBefore(arrival)){
            throw new RuntimeException(String.format("Arrival <'%s'> cannot be earlier than departure <'%s'>", arrival.toString(), departure.toString()));
        }
        this.departure = departure;
        this.arrival = arrival;
        this.departingCity = departingCity;
        this.destinationCity = destinationCity;
        this.distanceInKm = distanceInKm;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public String getDepartingCity(){
        return departingCity;
    }

    public String getDestinationCity(){
        return destinationCity;
    }
}
