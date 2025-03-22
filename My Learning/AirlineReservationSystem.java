import java.util.Scanner;

// Passenger class to store details
class Passenger {
    String passengerID, name, seatNumber, seatClass;
    int age, distance;
    double fare;

    // Constructor to initialize details
    Passenger(String passengerID, String name, int age, String seatNumber, String seatClass, int distance) {
        this.passengerID = passengerID;
        this.name = name;
        this.age = age;
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.distance = distance;
        this.fare = calculateFare(distance, age, seatClass);
    }

    // Overloaded methods for fare calculation
    double calculateFare(int distance) {
        return distance * 0.5; // Base fare per km
    }

    double calculateFare(int distance, int age, String seatClass) {
        double baseFare = calculateFare(distance);

        // Adjust fare based on age
        if (age < 12) {
            baseFare *= 0.5;  // Child pays 50%
        } else if (age >= 60) {
            baseFare *= 0.75; // Senior citizen pays 75%
        }

        // Adjust fare based on seat class
        if (seatClass.equalsIgnoreCase("Business")) {
            baseFare *= 1.5; // Business class pays 1.5x
        }

        return baseFare;
    }

    // Display passenger details
    void display() {
        System.out.printf("ID: %s, Name: %s, Seat: %s, Class: %s, Fare: $%.2f\n",
                          passengerID, name, seatNumber, seatClass, fare);
    }

    // Method to compare fares
    static Passenger compareFare(Passenger p1, Passenger p2) {
        return (p1.fare < p2.fare) ? p1 : p2;
    }
}

// Main class for Airline Reservation System
public class AirlineReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Array to store passengers
        Passenger[] passengers = new Passenger[50]; 
        int count = 0;  // Keeps track of number of passengers

        System.out.print("Enter number of passengers: ");
        int numPassengers = sc.nextInt();
        sc.nextLine(); // Consume newline 

        for (int i = 0; i < numPassengers; i++) {
            System.out.println("\nEnter details for Passenger " + (i + 1) + ":");
            System.out.print("ID: ");
            String id = sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Age: ");
            int age = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.print("Seat Number (e.g., 1A): ");
            String seat = sc.nextLine();
            System.out.print("Seat Class (Economy/Business): ");
            String seatClass = sc.nextLine();
            System.out.print("Distance (km): ");
            int distance = sc.nextInt();
            sc.nextLine(); // Consume newline

            // Create a Passenger object
            passengers[count++] = new Passenger(id, name, age, seat, seatClass, distance);
        }

        // Display all passenger details
        System.out.println("\n--- Airline Reservation Report ---");
        Passenger highestFare = passengers[0], lowestFare = passengers[0];

        for (int i = 0; i < count; i++) {
            passengers[i].display();
            if (passengers[i].fare > highestFare.fare) {
                highestFare = passengers[i];
            }
            if (passengers[i].fare < lowestFare.fare) {
                lowestFare = passengers[i];
            }
        }

        // Show highest and lowest fares
        System.out.printf("\nHighest Fare: %s ($%.2f)\n", highestFare.name, highestFare.fare);
        System.out.printf("Lowest Fare: %s ($%.2f)\n", lowestFare.name, lowestFare.fare);
        
        sc.close();
    }
}
