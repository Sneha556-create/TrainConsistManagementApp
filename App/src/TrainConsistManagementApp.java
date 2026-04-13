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

        // Create list of bogies (same as UC7)
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 50));
        bogies.add(new Bogie("First Class", 30));
        bogies.add(new Bogie("General", 90));

        // Convert to stream, filter, and collect
        List<Bogie> filteredBogies = bogies.stream()
                .filter(b -> b.capacity > 60)   // condition
                .collect(Collectors.toList());  // collect result

        // Display filtered bogies
        System.out.println("Bogies with capacity > 60:");
        for (Bogie b : filteredBogies) {
            System.out.println(b);
        }
    }
}