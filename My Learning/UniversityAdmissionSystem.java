import java.util.*;

abstract class Student {
    protected String name;
    protected int marks;
    protected String studentID;
    protected String type;
    protected double fees;
    
    public Student(String name, int marks, String type) {
        this.name = name;
        this.marks = marks;
        this.type = type;
        this.studentID = generateStudentID();
    }

    private String generateStudentID() {
        StringBuffer sb = new StringBuffer();
        if (type.equals("Undergraduate")) {
            sb.append("UG_");
        } else {
            sb.append("PG_");
        }
        sb.append(name.toUpperCase().split(" ")[0]); // First name in uppercase
        sb.append("_" + String.format("%03d", (int) (Math.random() * 900 + 100)));
        return sb.toString();
    }

    public abstract double calculateFinalFees();

    public void display() {
        System.out.println("\nStudent ID: " + studentID);
        System.out.println("Type: " + type);
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
        System.out.println("Fees: $" + fees);
        System.out.println("Final Payment: $" + calculateFinalFees());
    }
}

class Undergraduate extends Student {
    private double scholarship;

    public Undergraduate(String name, int marks) {
        super(name, marks, "Undergraduate");
        this.fees = 5000;
        this.scholarship = (marks >= 85) ? 500 : (marks >= 75) ? 300 : 0;
    }

    @Override
    public double calculateFinalFees() {
        return fees - scholarship;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Scholarship: $" + scholarship);
    }
}

class Postgraduate extends Student {
    private double discount;

    public Postgraduate(String name, int marks) {
        super(name, marks, "Postgraduate");
        this.fees = 10000;
        this.discount = (marks >= 90) ? 0.10 * fees : (marks >= 80) ? 0.05 * fees : 0;
    }

    @Override
    public double calculateFinalFees() {
        return fees - discount;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Discount: $" + discount);
    }
}

interface Payment {
    void processPayment(double amount);
}

class CreditCardPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Payment Method: Credit Card | Amount Paid: $" + amount);
    }
}

class BankTransferPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Payment Method: Bank Transfer | Amount Paid: $" + amount);
    }
}

class WalletPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Payment Method: Wallet | Amount Paid: $" + amount);
    }
}

public class UniversityAdmissionSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vector<Student> students = new Vector<>();
        HashMap<String, Payment> paymentMethods = new HashMap<>();

        paymentMethods.put("Credit Card", new CreditCardPayment());
        paymentMethods.put("Bank Transfer", new BankTransferPayment());
        paymentMethods.put("Wallet", new WalletPayment());

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1) + ":");
            System.out.print("Type (Undergraduate/Postgraduate): ");
            String type = sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Marks: ");
            int marks = sc.nextInt();
            sc.nextLine(); 
            System.out.print("Payment Method (Credit Card/Bank Transfer/Wallet): ");
            String paymentMethod = sc.nextLine();

            Student student = type.equalsIgnoreCase("Undergraduate") ? 
                              new Undergraduate(name, marks) : 
                              new Postgraduate(name, marks);
            students.add(student);

            System.out.println("\nGenerated Report:");
            student.display();

            Payment payment = paymentMethods.get(paymentMethod);
            if (payment != null) {
                payment.processPayment(student.calculateFinalFees());
            } else {
                System.out.println("Invalid Payment Method.");
            }
        }
        sc.close();
    }
}
