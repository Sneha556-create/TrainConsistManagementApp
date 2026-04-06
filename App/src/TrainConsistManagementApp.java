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

    // Maintains order
    private List<PassengerBogie> passengerBogies;

    // Ensures uniqueness
    private Set<String> bogieIds;

    public Train(String trainName) {
        this.trainName = trainName;
        this.passengerBogies = new ArrayList<>();
        this.bogieIds = new HashSet<>();
    }

    // ➕ Add bogie with uniqueness check
    public void addPassengerBogie(PassengerBogie bogie) {

        if (bogieIds.contains(bogie.getId())) {
            System.out.println("❌ Duplicate Bogie ID not allowed: " + bogie.getId());
            return;
        }

        passengerBogies.add(bogie);
        bogieIds.add(bogie.getId());

        System.out.println("✅ Added Bogie: " + bogie.getId());
    }

    // ❌ Remove bogie
    public void removePassengerBogie(String bogieId) {

        Iterator<PassengerBogie> iterator = passengerBogies.iterator();

        while (iterator.hasNext()) {
            PassengerBogie b = iterator.next();

            if (b.getId().equals(bogieId)) {
                iterator.remove();
                bogieIds.remove(bogieId);

                System.out.println("Removed Bogie: " + bogieId);
                return;
            }
        }

        System.out.println("Bogie not found: " + bogieId);
    }

    // 🔍 Check existence (O(1) using Set)
    public boolean containsBogie(String bogieId) {
        return bogieIds.contains(bogieId);
    }

    // 📋 Display
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
        train.addPassengerBogie(new PassengerBogie("BG101", "Sleeper", 72));
        train.addPassengerBogie(new PassengerBogie("BG102", "AC Chair", 50));

        // Attempt duplicate
        train.addPassengerBogie(new PassengerBogie("BG101", "First Class", 30));

        train.displayPassengerBogies();

        // -------------------- Check Existence --------------------
        System.out.println("\nCheck BG101: " + train.containsBogie("BG101"));
        System.out.println("Check BG999: " + train.containsBogie("BG999"));

        // -------------------- Remove --------------------
        train.removePassengerBogie("BG102");

        // Add again after removal (should work)
        train.addPassengerBogie(new PassengerBogie("BG102", "First Class", 30));

        train.displayPassengerBogies();
    }
}