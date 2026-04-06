import java.util.*;

// -------------------- Abstract Bogie --------------------
abstract class Bogie {
    protected String id;
    protected String type;

    public Bogie(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public abstract void displayDetails();
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

// -------------------- Train --------------------
class Train {
    private String trainName;
    private List<PassengerBogie> passengerBogies;

    public Train(String trainName) {
        this.trainName = trainName;
        this.passengerBogies = new ArrayList<>();
    }

    // ➕ Add bogie
    public void addPassengerBogie(PassengerBogie bogie) {
        passengerBogies.add(bogie);
        System.out.println("Added Bogie: " + bogie.getId());
    }

    // ❌ Remove bogie by ID
    public void removePassengerBogie(String bogieId) {
        Iterator<PassengerBogie> iterator = passengerBogies.iterator();

        while (iterator.hasNext()) {
            PassengerBogie b = iterator.next();
            if (b.getId().equals(bogieId)) {
                iterator.remove();
                System.out.println("Removed Bogie: " + bogieId);
                return;
            }
        }

        System.out.println("Bogie not found: " + bogieId);
    }

    // 🔍 Check existence
    public boolean containsBogie(String bogieId) {
        for (PassengerBogie b : passengerBogies) {
            if (b.getId().equals(bogieId)) {
                return true;
            }
        }
        return false;
    }

    // 📋 Display all bogies
    public void displayPassengerBogies() {
        System.out.println("\n🚆 Train: " + trainName);
        System.out.println("Passenger Bogies Count: " + passengerBogies.size());

        for (PassengerBogie b : passengerBogies) {
            b.displayDetails();
        }
    }
}

// -------------------- Main Application --------------------
public class TrainConsistManagementApp {

    public static void main(String[] args) {

        Train train = new Train("Express Line");

        // -------------------- Add Bogies --------------------
        train.addPassengerBogie(new PassengerBogie("P1", "Sleeper", 72));
        train.addPassengerBogie(new PassengerBogie("P2", "AC Chair", 50));
        train.addPassengerBogie(new PassengerBogie("P3", "First Class", 30));

        train.displayPassengerBogies();

        // -------------------- Check Existence --------------------
        System.out.println("\nCheck Bogie P2: " + train.containsBogie("P2"));
        System.out.println("Check Bogie P5: " + train.containsBogie("P5"));

        // -------------------- Remove Bogie --------------------
        train.removePassengerBogie("P2");
        train.removePassengerBogie("P5"); // not exists

        // -------------------- Final State --------------------
        train.displayPassengerBogies();
    }
}