import java.util.*;
import java.util.stream.*;

class GoodsBogie {
    String type;   // e.g., Cylindrical, Open, Box
    String cargo;  // e.g., Petroleum, Coal, Food

    // Constructor
    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    // Display format
    public String toString() {
        return type + " - " + cargo;
    }
}

public class TrainConsistManagementApp {
    public static void main(String[] args) {

        // Create list of goods bogies
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        bogies.add(new GoodsBogie("Box", "Food"));
        bogies.add(new GoodsBogie("Open", "Coal"));
        bogies.add(new GoodsBogie("Cylindrical", "Petroleum")); // valid

        // Stream + allMatch() for safety validation
        boolean isSafe = bogies.stream()
                .allMatch(b ->
                        // Rule: Cylindrical bogie → only Petroleum allowed
                        !(b.type.equalsIgnoreCase("Cylindrical")) ||
                                b.cargo.equalsIgnoreCase("Petroleum")
                );

        // Display result
        if (isSafe) {
            System.out.println("Train is SAFE and compliant ✅");
        } else {
            System.out.println("Train is NOT SAFE ❌");
        }
    }
}