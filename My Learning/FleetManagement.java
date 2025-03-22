import java.util.*;

class Vehicle
{

     int vehicle_ID;
     String vehicle_model;
     int load_capacity;
     int load;
     String vehicle_driver;
     int fuel_efficiency; 

    //methods
    public Vehicle(int vehicle_ID,String vehicle_model,int load_capacity,String vehicle_driver,int load,int fuel_efficiency)
    {
        this.vehicle_ID=vehicle_ID;
        this.vehicle_model=vehicle_model;
        this.load_capacity=load_capacity;
        this.vehicle_driver=vehicle_driver;
        this.load=load;
        this.fuel_efficiency=fuel_efficiency;
    } 
    void display()
    {
        System.out.print("ID:"+vehicle_ID+"Model:"+vehicle_model+"Driver:"+vehicle_driver+"Capacity:"+load_capacity+"Efficiency:"+fuel_efficiency+"km/l");
    }
    double fuel_consumption(int km){
        return km*fuel_efficiency;
    }
    int compare(Vehicle a,Vehicle b){
        if(a.fuel_efficiency>b.fuel_efficiency)
            return 1;
        else
            return 0;    
    }
}
class FleetManagement{

    public static void main(String[] args) {
        System.out.println("Number of Vehicle:");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Vehicle[] v= new Vehicle[n];


        for(int i=0;i<n;i++)
        {
            System.out.println("vehicle "+i+1+":");
            System.out.print("ID: ");
            int ID=sc.nextInt();
            System.out.print(" ,Model: ");
            String model=sc.nextLine();
            System.out.print(" ,Driver: ");
            String driver=sc.nextLine();
            System.out.print(" ,Capacity: ");
            int capacity=sc.nextInt();
            System.out.print(" ,Load: ");
            int load=sc.nextInt();
            System.out.print(" ,Efficiency: ");
            int efficiency=sc.nextInt();
            v[i]=new Vehicle(ID,model,capacity,driver,load,efficiency);
        }
        
    }
    




}