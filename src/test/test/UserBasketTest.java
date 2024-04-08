package test;

import models.Reservation;
import models.Train;
import models.UserBasket;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;

public class UserBasketTest {
    @Test
    public void getReservationsTest(){
        List<Reservation> reservations = List.of(
                new Reservation("admin", 15,
                        new Train(LocalTime.NOON, LocalTime.MIDNIGHT, "Vraca", "Sofia", 120)),
                new Reservation("admin", 26.72,
                        new Train(LocalTime.NOON, LocalTime.MIDNIGHT, "Sofia", "Varna", 400)),
                new Reservation("admin", 18.23,
                        new Train(LocalTime.NOON, LocalTime.MIDNIGHT, "Vidin", "Sofia", 270))
        );
        UserBasket userBasket = new UserBasket("admin", reservations);
        boolean isEqual = true;
        for(int i = 0; i < reservations.size(); i++){
            if(!reservations.get(i).equals(userBasket.getReservations().get(i))){
                isEqual = false;
                break;
            }
        }
        Assert.assertTrue(isEqual);
    }

    @Test
    public void addReservationTest(){
        List<Reservation> reservations = List.of(
                new Reservation("admin", 15,
                        new Train(LocalTime.NOON, LocalTime.MIDNIGHT, "Vraca", "Sofia", 120)),
                new Reservation("admin", 26.72,
                        new Train(LocalTime.NOON, LocalTime.MIDNIGHT, "Sofia", "Varna", 400)),
                new Reservation("admin", 18.23,
                        new Train(LocalTime.NOON, LocalTime.MIDNIGHT, "Vidin", "Sofia", 270))
        );
        UserBasket userBasket = new UserBasket("admin", null);
        for (Reservation reservation : reservations) {
            userBasket.addReservation(reservation);
        }
        boolean isEqual = true;
        for(int i = 0; i < reservations.size(); i++){
            if(!reservations.get(i).equals(userBasket.getReservations().get(i))){
                isEqual = false;
                break;
            }
        }
        Assert.assertTrue(isEqual);
    }

    @Test
    public void deleteReservationTest(){
        List<Reservation> reservations = new java.util.ArrayList<>(List.of(
                new Reservation("admin", 15,
                        new Train(LocalTime.NOON, LocalTime.MIDNIGHT, "Vraca", "Sofia", 120)),
                new Reservation("admin", 26.72,
                        new Train(LocalTime.NOON, LocalTime.MIDNIGHT, "Sofia", "Varna", 400)),
                new Reservation("admin", 18.23,
                        new Train(LocalTime.NOON, LocalTime.MIDNIGHT, "Vidin", "Sofia", 270))
        ));
        UserBasket userBasket = new UserBasket("admin", reservations);
        userBasket.deleteReservation(reservations.get(1));
        reservations.remove(reservations.get(1));
        boolean isEqual = true;
        for(int i = 0; i < reservations.size(); i++){
            if(!reservations.get(i).equals(userBasket.getReservations().get(i))){
                isEqual = false;
                break;
            }
        }
        Assert.assertTrue(isEqual);
    }
}
