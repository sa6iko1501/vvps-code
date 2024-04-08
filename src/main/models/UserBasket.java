package models;

import java.util.ArrayList;
import java.util.List;

public class UserBasket {
    private final String userName;
    private List<Reservation> reservations;

    public UserBasket(String userName, List<Reservation> reservations){
        if(userName == null || userName.isEmpty()){
            throw new RuntimeException("Invalid data for User");
        }
        this.userName = userName;
        if(reservations == null){
            this.reservations = new ArrayList<>();
        }
        else{
            this.reservations = reservations;
        }
    }
    public String getUserName() {
        return userName;
    }
    public List<Reservation> getReservations() {
        return reservations;
    }
    public void addReservation(Reservation reservation){
        if(reservation != null){
            reservations.add(reservation);
        }
    }
    public void deleteReservation(Reservation reservation){
        if(reservation != null){
            if(reservations.stream().anyMatch(x -> x.equals(reservation))) {
                reservations.remove(reservation);
            }
        }
    }
}
