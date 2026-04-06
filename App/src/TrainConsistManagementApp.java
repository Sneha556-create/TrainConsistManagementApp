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
    private int seatingCapacity;

    public PassengerBogie(String id, String type, int seatingCapacity) {
        super(id, type);
        this.seatingCapacity = seatingCapacity;
    }

    public int getCapacity() {
        return seatingCapacity;
    }

    @Override
    public void displayDetails() {
        System.out.println("Passenger Bogie | ID: " + id +
                ", Type: " + type +
                ", Seating Capacity: " + seatingCapacity);
    }
}

// -------------------- Goods Bogie --------------------
class GoodsBogie extends Bogie {
    private int loadCapacity; // in tons

    public GoodsBogie(String id, String type, int loadCapacity) {
        super(id, type);
        this.loadCapacity = loadCapacity;
    }

    public int getCapacity() {
        return loadCapacity;
    }

    @Override
    public void displayDetails() {
        System.out.println("Goods Bogie | ID: " + id +
                ", Type: " + type +
                ", Load Capacity: " + loadCapacity + " tons");
    }
}

// -------------------- Train --------------------
class Train {
    private String trainName;

    // Preserve insertion order + uniqueness
    private LinkedHashSet<Bogie> bogies;

    // Quick lookup by ID
    private Map<String, Bogie> bogieMap;

    // Map bogie ID → capacity
    private Map<String, Integer> capacityMap;

    public Train(String trainName) {
        this.trainName = trainName;
        this.bogies = new LinkedHashSet<>();
        this.bogieMap = new HashMap<>();
        this.capacityMap = new HashMap<>();
    }

    // ➕ Add bogie
    public void addBogie(Bogie bogie) {
        if (bogieMap.containsKey(bogie.getId())) {
            System.out.println("❌ Duplicate Bogie ID not allowed: " + bogie.getId());
            return;
        }

        bogies.add(bogie);
        bogieMap.put(bogie.getId(), bogie);

        // Add capacity mapping
        if (bogie instanceof PassengerBogie) {
            capacityMap.put(bogie.getId(), ((PassengerBogie) bogie).getCapacity());
        } else if (bogie instanceof GoodsBogie) {
            capacityMap.put(bogie.getId(), ((GoodsBogie) bogie).getCapacity());
        }

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
        capacityMap.remove(bogieId);

        System.out.println("Removed Bogie: " + bogieId);
    }

    // 🔍 Check existence
    public boolean containsBogie(String bogieId) {
        return bogieMap.containsKey(bogieId);
    }

    // 📋 Display sequence
    public void displayTrainSequence() {
        System.out.println("\n🚆 Train: " + trainName);
        System.out.println("Total Bogies: " + bogies.size());
        for (Bogie b : bogies) {
            b.displayDetails();
        }
    }

    // 📊 Display capacity mapping
    public void displayCapacityMap() {
        System.out.println("\n📊 Bogie Capacities:");
        for (String bogieId : capacityMap.keySet()) {
            System.out.println("Bogie ID: " + bogieId + ", Capacity: " + capacityMap.get(bogieId));
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
        train.addBogie(new GoodsBogie("G1", "Rectangular", 100));
        train.addBogie(new GoodsBogie("G2", "Cylindrical", 80));

        // Attempt duplicate
        train.addBogie(new PassengerBogie("P2", "First Class", 30));

        // -------------------- Display Sequence --------------------
        train.displayTrainSequence();

        // -------------------- Display Capacity Mapping --------------------
        train.displayCapacityMap();

        // -------------------- Remove & Re-add --------------------
        train.removeBogie("G1");
        train.addBogie(new GoodsBogie("G3", "Rectangular", 120));

        train.displayTrainSequence();
        train.displayCapacityMap();
    }
}