import java.util.*;

// -------------------- Abstract Bogie --------------------
abstract class Bogie {
    protected String id;
    protected String type;

    public Bogie(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public abstract void displayDetails();

    public String getType() {
        return type;
    }
}

// -------------------- Passenger Bogie --------------------
class PassengerBogie extends Bogie {
    private int capacity;

    public PassengerBogie(String id, String type, int capacity) {
        super(id, type);
        this.capacity = capacity;
    }

    @Override
    public void displayDetails() {
        System.out.println("Passenger Bogie | ID: " + id +
                ", Type: " + type +
                ", Capacity: " + capacity);
    }
}

// -------------------- Goods Bogie --------------------
class GoodsBogie extends Bogie {
    private String cargoType;

    public GoodsBogie(String id, String type, String cargoType) {
        super(id, type);
        this.cargoType = cargoType;
    }

    @Override
    public void displayDetails() {
        System.out.println("Goods Bogie | ID: " + id +
                ", Type: " + type +
                ", Cargo: " + cargoType);
    }
}

// -------------------- Train --------------------
class Train {
    private String trainName;
    private List<Bogie> bogies;

    public Train(String trainName) {
        this.trainName = trainName;
        this.bogies = new ArrayList<>();
    }

    public void addBogie(Bogie bogie) {
        bogies.add(bogie);
    }

    public void displaySummary() {
        System.out.println("\n🚆 Train: " + trainName);
        System.out.println("Total Bogies: " + bogies.size());

        int passengerCount = 0;
        int goodsCount = 0;

        for (Bogie b : bogies) {
            if (b instanceof PassengerBogie) {
                passengerCount++;
            } else if (b instanceof GoodsBogie) {
                goodsCount++;
            }
        }

        System.out.println("Passenger Bogies: " + passengerCount);
        System.out.println("Goods Bogies: " + goodsCount);

        System.out.println("\n--- Bogie Details ---");
        for (Bogie b : bogies) {
            b.displayDetails();
        }
    }
}

// -------------------- Main Application --------------------
public class TrainConsistManagementgit add .App {

    public static void main(String[] args) {

        // Step 1: Initialize Train
        Train train = new Train("Express Line");

        // Step 2: Add Passenger Bogies
        train.addBogie(new PassengerBogie("P1", "Sleeper", 72));
        train.addBogie(new PassengerBogie("P2", "AC Chair", 50));
        train.addBogie(new PassengerBogie("P3", "First Class", 30));

        // Step 3: Add Goods Bogies
        train.addBogie(new GoodsBogie("G1", "Rectangular", "Coal"));
        train.addBogie(new GoodsBogie("G2", "Cylindrical", "Oil"));

        // Step 4: Display Summary
        train.displaySummary();
    }
}
