import java.util.Scanner;

class TrafficMonitoringSystem {
    int[] vehicle_count = new int[4]; // 4 road segments per intersection
    int[][] vehicle_speed = new int[24][4]; // 24 hours, 4 road segments

    // Method to take vehicle speed input
    void get_data_vehicle_speed(Scanner sc, char intersection) {
        System.out.println("Enter hourly vehicle speeds (km/hr) for North, South, East, and West at intersection " + intersection + ":");
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 4; j++) {
                vehicle_speed[i][j] = sc.nextInt();
            }
        }
    }

    // Method to take vehicle count input
    void get_data_vehicle_count(Scanner sc, char intersection) {
        System.out.println("Enter vehicle counts for North, South, East, and West at intersection " + intersection + ":");
        for (int j = 0; j < 4; j++) {
            vehicle_count[j] = sc.nextInt();
        }
    }

    // Method to calculate the average speed
    double calculate_avg_speed() {
        double avg_speed = 0;
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 4; j++) {
                avg_speed += vehicle_speed[i][j];
            }
        }
        return avg_speed / 96; // 24 hours * 4 segments
    }

    // Method to calculate total vehicle count
    int calculate_total_vehicle_count() {
        int total_vehicle_count = 0;
        for (int i = 0; i < 4; i++) {
            total_vehicle_count += vehicle_count[i];
        }
        return total_vehicle_count;
    }

    // Method to determine congestion level
    String getCongestionLevel(double avgSpeed) {
        if (avgSpeed < 30) {
            return "High";
        } else if (avgSpeed >= 30 && avgSpeed < 60) {
            return "Medium";
        } else {
            return "Low";
        }
    }

    // Method to display the traffic report for an intersection
    void display_report(char intersection) {
        double avgSpeed = calculate_avg_speed();
        int totalVehicles = calculate_total_vehicle_count();
        String congestion = getCongestionLevel(avgSpeed);

        System.out.println("\nIntersection " + intersection + " Traffic Report:");
        System.out.printf("- Average vehicle speed: %.1f km/h%n", avgSpeed);
        System.out.println("- Total vehicle count: " + totalVehicles);
        System.out.println("- Congestion Level: " + congestion);
        System.out.println("----------------------------------");
    }
}

public class TrafficReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Array to store 5 intersections
        TrafficMonitoringSystem[] intersections = new TrafficMonitoringSystem[5];
        char[] intersectionNames = {'A', 'B', 'C', 'D', 'E'};

        // Create objects for each intersection
        for (int i = 0; i < 5; i++) {
            intersections[i] = new TrafficMonitoringSystem();
            intersections[i].get_data_vehicle_speed(sc, intersectionNames[i]);
            intersections[i].get_data_vehicle_count(sc, intersectionNames[i]);
        }

        // Display reports for all intersections
        System.out.println("\nSmart City Traffic Monitoring Report:");
        for (int i = 0; i < 5; i++) {
            intersections[i].display_report(intersectionNames[i]);
        }

        // Display citywide totals
        display_citywide_totals(intersections, intersectionNames);

        sc.close(); // Close scanner
    }

    // Method to display citywide totals
    static void display_citywide_totals(TrafficMonitoringSystem[] intersections, char[] intersectionNames) {
        int totalVehiclesMonitored = 0;
        int highCongestionCount = 0;

        // Calculate total vehicles and high congestion intersections
        for (int i = 0; i < 5; i++) {
            totalVehiclesMonitored += intersections[i].calculate_total_vehicle_count();
            if (intersections[i].getCongestionLevel(intersections[i].calculate_avg_speed()).equals("High")) {
                highCongestionCount++;
            }
        }

        // Display citywide report
        System.out.println("\nCitywide Totals:");
        System.out.println("- Total Vehicles Monitored: " + totalVehiclesMonitored);
        System.out.println("- High Congestion Intersections: " + highCongestionCount);
        System.out.println("----------------------------------");
    }
}
