import java.util.*;
import java.io.*;

// Book class holds the details of each book.
class Book {
    String title;
    String author;
    double price;
    int bookId;
    int copies;

    // Constructor to initialize a new Book.
    public Book(String title, String author, double price, int bookId, int copies) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.bookId = bookId;
        this.copies = copies;
    }

    // Override toString() for a friendly display of book details.
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author +
               ", Price: " + price + ", Copies: " + copies;
    }
}

public class Library_Management {
    
    // Bubble sort to sort books based on price in ascending order.
    public static void sortBooksByPrice(Book[] books, int count) {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (books[j].price > books[j + 1].price) {
                    // Swap books[j] and books[j+1]
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
    }

    // Search for books by title or author (case-insensitive).
    public static void searchBooks(Book[] books, int count, String query) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (books[i].title.toLowerCase().contains(query.toLowerCase()) ||
                books[i].author.toLowerCase().contains(query.toLowerCase())) {
                System.out.println(books[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found matching the query: " + query);
        }
    }

    // Issue a book (reduce available copies if possible)
    public static void issueBook(Book[] books, int count, int id) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (books[i].bookId == id) {
                found = true;
                if (books[i].copies > 0) {
                    books[i].copies--;
                    System.out.println("Book issued successfully: " + books[i].title);
                } else {
                    System.out.println("No copies available for: " + books[i].title);
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    // Return a book (increase available copies)
    public static void returnBook(Book[] books, int count, int id) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (books[i].bookId == id) {
                found = true;
                books[i].copies++;
                System.out.println("Book returned successfully: " + books[i].title);
                break;
            }
        }
        if (!found) {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    // Save the library data to a file.
    public static void saveLibraryToFile(Book[] books, int count, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Write each book's details in CSV format.
            for (int i = 0; i < count; i++) {
                writer.println(books[i].bookId + "," + books[i].title + "," + 
                               books[i].author + "," + books[i].price + "," + books[i].copies);
            }
            System.out.println("Library data saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Main method to interact with the user.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask user for the maximum library size.
        System.out.print("Enter the total capacity of the library: ");
        int size = sc.nextInt();
        sc.nextLine(); // Consume newline

        // Create an array of Book objects.
        Book[] books = new Book[size];
        int count = 0; // Number of books currently in the library

        int choice = 0;
        do {
            // Display the menu options.
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add a Book");
            System.out.println("2. Search for a Book by Title/Author");
            System.out.println("3. Sort Books by Price (Ascending)");
            System.out.println("4. Issue a Book");
            System.out.println("5. Return a Book");
            System.out.println("6. Save Library Data to File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Adding a new book.
                    if (count < size) {
                        System.out.println("\nEnter details for Book " + (count + 1) + ":");
                        System.out.print("Title: ");
                        String title = sc.nextLine();
                        System.out.print("Author: ");
                        String author = sc.nextLine();
                        System.out.print("Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Book ID: ");
                        int bookId = sc.nextInt();
                        System.out.print("Copies: ");
                        int copies = sc.nextInt();
                        sc.nextLine(); // Consume newline

                        // Create a new Book object and add it to the array.
                        books[count] = new Book(title, author, price, bookId, copies);
                        count++;
                    } else {
                        System.out.println("Library is full. Cannot add more books.");
                    }
                    break;

                case 2:
                    // Searching for a book by title or author.
                    System.out.print("Enter the title or author to search: ");
                    String query = sc.nextLine();
                    searchBooks(books, count, query);
                    break;

                case 3:
                    // Sorting books by price.
                    sortBooksByPrice(books, count);
                    System.out.println("Books sorted by price in ascending order:");
                    for (int i = 0; i < count; i++) {
                        System.out.println(books[i]);
                    }
                    break;

                case 4:
                    // Issuing a book.
                    System.out.print("Enter the Book ID to issue: ");
                    int issueId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    issueBook(books, count, issueId);
                    break;

                case 5:
                    // Returning a book.
                    System.out.print("Enter the Book ID to return: ");
                    int returnId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    returnBook(books, count, returnId);
                    break;

                case 6:
                    // Saving library data to a file.
                    System.out.print("Enter filename to save library data (e.g., library.txt): ");
                    String filename = sc.nextLine();
                    saveLibraryToFile(books, count, filename);
                    break;

                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        sc.close();
    }
}
