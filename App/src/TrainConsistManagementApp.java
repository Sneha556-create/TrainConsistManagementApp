import java.util.*;
import java.util.stream.*;

class Bogie {
    String name;
    int capacity;

    // Constructor
    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    // Display format
    public String toString() {
        return name + " - Capacity: " + capacity;
    }
}

public class TrainConsistManagementApp {
    public static void main(String[] args) {

        // Create list of bogies
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 50));
        bogies.add(new Bogie("First Class", 30));
        bogies.add(new Bogie("General", 90));

        // Stream pipeline: map + reduce
        int totalCapacity = bogies.stream()
                .map(b -> b.capacity)          // extract capacities
                .reduce(0, Integer::sum);      // sum all values

        // Display result
        System.out.println("Total Seating Capacity: " + totalCapacity);
    }
}