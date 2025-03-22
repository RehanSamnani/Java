import java.io.*;
import java.util.Scanner;

class User {
    private String name;
    private String email;
    private String password;
    private String role; // Citizen or Admin

    // Constructor
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Convert user data to a formatted string
    public String toCSV() {
        return name + "," + email + "," + password + "," + role;
    }

    public String getEmail() {
        return email;
    }
}

class UserManagement {
    private static final String FILE_NAME = "users.txt";

    // Method to register a new user
    public void registerUser(User user) {
        try {
            // Check if email is already registered
            if (isEmailRegistered(user.getEmail())) {
                System.out.println("❌ Error: Email already registered!");
                return;
            }
            
            // Append user details to file
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
            writer.write(user.toCSV());
            writer.newLine();
            writer.close();

            System.out.println("✅ Registration successful!");

        } catch (IOException e) {
            System.out.println("Error while saving user data.");
        }
    }

    // Check if an email is already registered
    private boolean isEmailRegistered(String email) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return false; // If file doesn't exist, no emails are registered
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 2 && userData[1].equals(email)) { // Avoid IndexOutOfBoundsException
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading user data.");
        }
        return false;
    }

    // Method to authenticate user login
    public String loginUser(String email, String password) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("❌ No users registered yet.");
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");

                if (userData.length >= 4) { // Ensure data is valid
                    if (userData[1].equals(email)) { // Email match found
                        if (userData[2].equals(password)) { // Password match
                            return userData[3]; // Return role (Citizen/Admin)
                        } else {
                            throw new Exception("❌ Incorrect Password! Please try again.");
                        }
                    }
                }
            }
            throw new Exception("❌ User not found! Please register first.");
        } catch (IOException e) {
            System.out.println("Error while reading user data.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

public class UserManagementsSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManagement userMgmt = new UserManagement();

        try {
            while (true) {
                System.out.println("\n🔹 USER MANAGEMENT SYSTEM 🔹");
                System.out.println("1️⃣ Register New User");
                System.out.println("2️⃣ Login");
                System.out.println("3️⃣ Exit");
                System.out.print("Select an option: ");

                int choice;
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                } catch (Exception e) {
                    System.out.println("❌ Invalid input! Please enter a number.");
                    scanner.nextLine(); // Consume invalid input
                    continue;
                }

                switch (choice) {
                    case 1:
                        // Get user input for registration
                        System.out.println("\n📌 Register a New User");
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Enter Password: ");
                        String password = scanner.nextLine();

                        System.out.print("Enter Role (Citizen/Admin): ");
                        String role = scanner.nextLine();

                        // Validate inputs
                        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty()) {
                            System.out.println("❌ Error: All fields are required!");
                        } else if (!role.equalsIgnoreCase("Citizen") && !role.equalsIgnoreCase("Admin")) {
                            System.out.println("❌ Error: Invalid role! Please enter 'Citizen' or 'Admin'.");
                        } else {
                            // Create user object and register
                            User newUser = new User(name, email, password, role);
                            userMgmt.registerUser(newUser);
                        }
                        break;

                    case 2:
                        // Get login input
                        System.out.println("\n🔐 User Login");
                        System.out.print("Enter Email: ");
                        String loginEmail = scanner.nextLine();

                        System.out.print("Enter Password: ");
                        String loginPassword = scanner.nextLine();

                        // Authenticate user
                        String userRole = userMgmt.loginUser(loginEmail, loginPassword);

                        if (userRole != null) {
                            System.out.println("✅ Login Successful! Welcome, " + userRole);
                            if (userRole.equalsIgnoreCase("Admin")) {
                                System.out.println("📌 Admin Panel Access Granted!");
                            } else {
                                System.out.println("📌 Citizen Services Access Granted!");
                            }
                        }
                        break;

                    case 3:
                        System.out.println("👋 Exiting... Have a great day!");
                        return;

                    default:
                        System.out.println("❌ Invalid option! Please try again.");
                }
            }
        } finally {
            scanner.close(); // Close scanner at the end
        }
    }
}
