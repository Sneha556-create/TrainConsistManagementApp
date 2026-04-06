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

    // Preserve insertion order + uniqueness
    private LinkedHashSet<Bogie> bogies;

    // Quick lookup by ID
    private Map<String, Bogie> bogieMap;

    public Train(String trainName) {
        this.trainName = trainName;
        this.bogies = new LinkedHashSet<>();
        this.bogieMap = new HashMap<>();
    }

    // ➕ Add bogie
    public void addBogie(Bogie bogie) {
        if (bogieMap.containsKey(bogie.getId())) {
            System.out.println("❌ Duplicate Bogie ID not allowed: " + bogie.getId());
            return;
        }

        bogies.add(bogie);
        bogieMap.put(bogie.getId(), bogie);
        System.out.println("✅ Added Bogie: " + bogie.getId());
    }

    // ❌ Remove bogie by ID
    public void removeBogie(String bogieId) {
        if (!bogieMap.containsKey(bogieId)) {
            System.out.println("Bogie not found: " + bogieId);
            return;
        }

        Bogie b = bogieMap.get(bogieId);
        bogies.remove(b);
        bogieMap.remove(bogieId);

        System.out.println("Removed Bogie: " + bogieId);
    }

    // 🔍 Check existence
    public boolean containsBogie(String bogieId) {
        return bogieMap.containsKey(bogieId);
    }

    // 📋 Display in insertion order
    public void displayTrainSequence() {
        System.out.println("\n🚆 Train: " + trainName);
        System.out.println("Total Bogies: " + bogies.size());
        for (Bogie b : bogies) {
            b.displayDetails();
        }
    }
}

// -------------------- Main Application --------------------
public class TrainConsistManagementApp {

    public static void main(String[] args) {

        Train train = new Train("Express Line");

        // -------------------- Add Bogies --------------------
        train.addBogie(new PassengerBogie("P1", "Sleeper", 72));
        train.addBogie(new PassengerBogie("P2", "AC Chair", 50));
        train.addBogie(new GoodsBogie("G1", "Rectangular", "Coal"));
        train.addBogie(new GoodsBogie("G2", "Cylindrical", "Oil"));

        // Attempt duplicate
        train.addBogie(new PassengerBogie("P2", "First Class", 30));

        // -------------------- Display Sequence --------------------
        train.displayTrainSequence();

        // -------------------- Remove & Re-add --------------------
        train.removeBogie("G1");
        train.addBogie(new GoodsBogie("G3", "Rectangular", "Grain"));

        train.displayTrainSequence();
    }
}