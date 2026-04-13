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

    // Method to categorize bogies based on capacity
    public String getCategory() {
        if (capacity >= 70) return "High Capacity";
        else if (capacity >= 40) return "Medium Capacity";
        else return "Low Capacity";
    }

    // Display format
    public String toString() {
        return name + " (" + capacity + ")";
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

        // Group bogies using Stream API
        Map<String, List<Bogie>> groupedBogies = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.getCategory()));

        // Display grouped result
        System.out.println("Grouped Bogies:");
        for (String category : groupedBogies.keySet()) {
            System.out.println(category + ":");
            for (Bogie b : groupedBogies.get(category)) {
                System.out.println("  " + b);
            }
        }
    }
}