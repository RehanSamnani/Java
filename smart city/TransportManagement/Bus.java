package TransportManagement;
import java.util.*;
import java.io.*;

class Bus{
    int bus_no;
    String Driver;
    String Conductor;
    int capacity;
    int Available_seats;
    boolean[] seats; // true = available, false = occupied
    String model;
    float total_distance;
    String Source;
    String Destination;
    String Route;
    String Arrival_time;
    String termination_time;
    String Bus_type;
    String Bus_company;
    String[] Bus_stop;
    float total_journey_time;
    float[] fare;
    String Date;

    //default constructor with random values
    Bus(){
        generateRandomData();
        saveBusData();
    }
    //method to provide values using random values to objects of bus class 
    public void generateRandomData() {
        Random rand = new Random();

        this.bus_no = rand.nextInt(1000) + 100; // Random bus number between 100-1099

        String[] drivers = {"John Doe", "Alex Smith", "Michael Brown", "David Johnson"};
        this.Driver = drivers[rand.nextInt(drivers.length)];

        String[] conductors = {"James Wilson", "Robert Martinez", "William Anderson", "Richard Thomas"};
        this.Conductor = conductors[rand.nextInt(conductors.length)];

        this.capacity = rand.nextInt(51) + 30; // Capacity between 30-80

        String[] models = {"Volvo B11R", "Scania F310", "Tata Starbus", "Mercedes-Benz"};
        this.model = models[rand.nextInt(models.length)];

        this.total_distance = Math.round((50 + (200 * rand.nextFloat())) * 100.0f) / 100.0f; // Distance between 50-250 km

        String[] cities = {"Delhi", "Mumbai", "Kolkata", "Chennai", "Bengaluru", "Hyderabad", "Pune", "Ahmedabad", "Jaipur", "Lucknow", 
        "Chandigarh", "Bhopal", "Indore", "Patna", "Guwahati", "Bhubaneswar", "Surat", "Kochi", "Visakhapatnam", "Nagpur", "Kanpur", "Agra"};
        this.Source = cities[rand.nextInt(cities.length)];
        this.Destination = cities[rand.nextInt(cities.length)];
        while (this.Destination.equals(this.Source)) {
            this.Destination = cities[rand.nextInt(cities.length)];
        }

        this.Route = this.Source + " → " + this.Destination;

        String[] times = {"06:30 AM", "08:00 AM", "12:30 PM", "03:45 PM", "07:15 PM", "10:00 PM", "11:30 PM"};
        this.Arrival_time = times[rand.nextInt(times.length)];
        this.termination_time = times[rand.nextInt(times.length)];

        String[] bus_types = {"AC", "Non-AC", "Luxury", "Sleeper", "Semi-Sleeper"};
        this.Bus_type = bus_types[rand.nextInt(bus_types.length)];

        String[] companies = {"Greyhound", "Megabus", "FlixBus", "RedBus"};
        this.Bus_company = companies[rand.nextInt(companies.length)];

        String[] stops = {"Faridabad", "Ghaziabad", "Noida", "Gurugram", "Meerut", "Aligarh", "Mathura", "Ajmer", "Ujjain", "Jhansi", 
                        "Gwalior", "Muzaffarpur", "Gaya", "Darbhanga", "Bhagalpur", "Jamshedpur", "Dhanbad", "Rourkela", "Sambalpur", 
                        "Cuttack", "Bilaspur", "Durg", "Korba", "Raigarh", "Satna", "Rewa", "Sagar", "Itarsi", "Burhanpur", "Ratlam", 
                        "Jalgaon", "Kolhapur", "Solapur", "Nanded", "Latur", "Belgaum", "Dharwad", "Davangere", "Tumkur", "Kurnool", 
                        "Nellore", "Rajahmundry", "Kakinada", "Eluru", "Warangal", "Karimnagar", "Nizamabad", "Sangli", "Malegaon", 
                        "Navsari", "Anand", "Bharuch", "Vapi", "Valsad", "Bhuj", "Morbi", "Palanpur", "Bharatpur", "Bundi", "Chittorgarh"};
                        
        this.Bus_stop = new String[rand.nextInt(11) + 10]; // Random 10 to 20 stops
        for (int i = 0; i < this.Bus_stop.length; i++) {
            this.Bus_stop[i] = stops[rand.nextInt(stops.length)];
        }

        this.total_journey_time = Math.round((3 + (12* rand.nextFloat())) * 100.0f) / 100.0f; // Time between 3-15 hours

        this.fare = new float[this.Bus_stop.length];
        for (int i = 0; i < this.fare.length; i++) {
            this.fare[i] = Math.round((5 + (50 * rand.nextFloat())) * 100.0f) / 100.0f; // Fare between $5-$55
        }
    }
 
    //constructor
    Bus(int bus_no, String Driver, String Conductor, int capacity, String model, float total_distance, String Source, String Destination, String Route, String Arrival_time, String termination_time, String Bus_type, String Bus_company, String[] Bus_stop, float total_journey_time, float[] fare){
        this.bus_no = bus_no;
        this.Driver = Driver;
        this.Conductor = Conductor;
        this.capacity = capacity;
        this.model = model;
        this.total_distance = total_distance;
        this.Source = Source;
        this.Destination = Destination;
        this.Route = Route;
        this.Arrival_time = Arrival_time;
        this.termination_time = termination_time;
        this.Bus_type = Bus_type;
        this.Bus_company = Bus_company;
        this.Bus_stop = Bus_stop;
        this.total_journey_time = total_journey_time;
        this.fare = fare;
    }
    
    //getter
    public int getBus_no(){
        return bus_no;
    }
    public String getDriver(){
        return Driver;
    }
    public String getConductor(){
        return Conductor;
    }
    public int getCapacity(){
        return capacity;
    }
    public String getModel(){
        return model;
    }
    public float getTotal_distance(){
        return total_distance;
    }
    public String getSource(){
        return Source;
    }
    public String getDestination(){
        return Destination;
    }
    public String getRoute(){
        return Route;
    }
    public String getArrival_time(){
        return Arrival_time;
    }
    public String getTermination_time(){
        return termination_time;
    }
    public String getBus_type(){
        return Bus_type;
    }
    public String getBus_company(){
        return Bus_company;
    }
    public String[] getBus_stop(){
        return Bus_stop;
    }
    public float getTotal_journey_time(){
        return total_journey_time;
    }
    public float[] getFare(){
        return fare;
    }
    public String getDate(){
        return Date;
    }


    //method to display the details of the bus
    public void display(){
        System.out.println("Bus Number: " + bus_no);
        System.out.println("Driver: " + Driver);
        System.out.println("Conductor: " + Conductor);
        System.out.println("Capacity: " + capacity);
        System.out.println("Model: " + model);
        System.out.println("Total Distance: " + total_distance + " km");
        System.out.println("Source: " + Source);
        System.out.println("Destination: " + Destination);
        System.out.println("Route: " + Route);
        System.out.println("Arrival Time: " + Arrival_time);
        System.out.println("Termination Time: " + termination_time);
        System.out.println("Bus Type: " + Bus_type);
        System.out.println("Bus Company: " + Bus_company);
        System.out.println("Bus Stops: ");
        for (int i = 0; i < Bus_stop.length; i++) {
            System.out.println(Bus_stop[i]);
        }
        System.out.println("Total Journey Time: " + total_journey_time + " hours");
        System.out.println("Fare: ");
        for (int i = 0; i < fare.length; i++) {
            System.out.println("Stop " + (i + 1) + ": $" + fare[i]);
        }
        System.out.println("----------------------------------------------------------------------------");
    }
    
    // Convert user data to a formatted string
    public String toCSV() {
        String stops = "";
        for (int i = 0; i < Bus_stop.length; i++) {
            stops += Bus_stop[i];
            if (i < Bus_stop.length - 1) {
                stops += "|";
            }
        }

        String fares = "";
        for (int i = 0; i < fare.length; i++) {
            fares += fare[i];
            if (i < fare.length - 1) {
                fares += "|";
            }
        }

        return bus_no + "," + Driver + "," + Conductor + "," + capacity + "," + model + "," + total_distance + "," + Source + "," + Destination + "," + Route + "," + Arrival_time + "," + termination_time + "," + Bus_type + "," + Bus_company + "," + stops + "," + total_journey_time + "," + fares;
    }

     // Append bus details to file
    public void saveBusData() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("buses.txt", true));
            writer.write(toCSV());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving bus data.");
        }
    }

    // Check if a bus number is already registered
    public static boolean isBusNumberRegistered(int bus_no) {
        File file = new File("buses.txt");
        if (!file.exists()) {
            return false; // If file doesn't exist, no buses are registered
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] busData = line.split(",");
                if (busData.length >= 1 && Integer.parseInt(busData[0]) == bus_no) { // Avoid IndexOutOfBoundsException
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading bus data.");
        }
        return false;
    }

    void Available_bus(List<Bus> b, String source, String destination, String bus_type, String date, String time) {
        for (Bus bus : b) {
            if (bus.Source.equals(source) && bus.Destination.equals(destination) && bus.Bus_type.equals(bus_type) && bus.Date.equals(date)) {
                System.out.println("Bus Number: " + bus.bus_no);
                System.out.println("Driver: " + bus.Driver);
                System.out.println("Conductor: " + bus.Conductor);
                System.out.println("Route: " + bus.Route);
                System.out.println("Arrival Time: " + bus.Arrival_time);
                System.out.println("Termination Time: " + bus.termination_time);
                System.out.println("Bus Type: " + bus.Bus_type);
                System.out.println("Bus Company: " + bus.Bus_company);
                System.out.println("Fare: $" + bus.fare[bus.fare.length - 1]);
                System.out.println("----------------------------------------------------------------------------");
            }
        }
    }
    
    public static List<Bus> load_Buses_from_file(String filename) {
        List<Bus> buslist = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
    
                int bus_no = Integer.parseInt(data[0]);
                String Driver = data[1];
                String Conductor = data[2];
                int capacity = Integer.parseInt(data[3]);
                String model = data[4];
                float total_distance = Float.parseFloat(data[5]);
                String Source = data[6];
                String Destination = data[7];
                String Route = data[8];
                String Arrival_time = data[9];
                String termination_time = data[10];
                String Bus_type = data[11];
                String Bus_company = data[12];
    
                // Fix the split error
                String[] Bus_stop = data[13].split("\\|");
                float total_journey_time = Float.parseFloat(data[14]);
    
                // Fix the fare split error
                String[] fare_str = data[15].split("\\|");
                float[] fare = new float[fare_str.length];
                for (int i = 0; i < fare_str.length; i++) {
                    fare[i] = Float.parseFloat(fare_str[i]);
                }
    
                // Add missing Available_seats initialization
                int Available_seats = capacity; // Assuming initially all seats are available
    
                Bus bus = new Bus(bus_no, Driver, Conductor, capacity, model, total_distance, Source, Destination, Route, Arrival_time, termination_time, Bus_type, Bus_company, Bus_stop, total_journey_time, fare);
                bus.Available_seats = Available_seats; // Assign available seats
                buslist.add(bus);
            }
        } catch (IOException e) {
            System.out.println("Error while reading bus data.");
        }
        return buslist;
    }
    
    //provide number of seats available
    public int Available_seats(List<Bus> b, int bus_no) {
        for (Bus bus : b) {
            if (bus.bus_no == bus_no) {
                return bus.Available_seats;
            }
        }
        return -1;
    }


    /*public static String generate_PNR(Bus b,int seat_no)
    {
    
    }*/
    //book the seat
    public void book_seat(List<Bus> b, int bus_no, int number_seat) {
        for (Bus bus : b) {
            if (bus.bus_no == bus_no) {
                if (bus.Available_seats >= number_seat) {
                    for (int i = 0; i < number_seat; i++) {
                        bus.Available_seats -= 1; // Reduce available seats
                        System.out.println("Your PNR number is: " /*+ generate_PNR(bus, bus.Available_seats)*/);
                    }
                } else {
                    System.out.println("Seats Unavailable");
                }
                break;
            }
        }
    }
    


}    