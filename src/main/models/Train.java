package models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

public class Train {
    private final LocalTime departure;
    private final LocalTime arrival;
    private final double distanceInKm;
    private final String departingCity;

    private final String destinationCity;

    public Train(LocalTime departure, LocalTime arrival, String departingCity, String destinationCity,
                 double distanceInKm) {
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

    public LocalTime getDeparture() {
        return departure;
    }

    public LocalTime getArrival() {
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
