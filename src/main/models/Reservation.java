package models;

public class Reservation {
    private final String owner;
    private double price;
    private Train train;

    public Reservation(String owner, double price, Train train){
        if(owner == null || owner.isEmpty() || price <= 0 || train == null){
            throw new RuntimeException("Invalid data for Reservation");
        }
        this.owner = owner;
        this.price = price;
        this.train = train;
    }

    public String getOwner() {
        return owner;
    }

    public double getPrice() {
        return price;
    }

    public Train getTrain() {
        return train;
    }

    public void setPrice(double price) {
        if(price >= 0){
            this.price = price;
        }
    }

    public void setTrain(Train train) {
        if(train != null){
            this.train = train;
        }
    }
}
