package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryDAO libraryDAO = new LibraryDAOImpl();

        while (true) {
            System.out.println("\n========== Library Management System ==========");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Add Member");
            System.out.println("4. Delete Book");
            System.out.println("5. Delete Member");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter total quantity: ");
                    int totalQty = sc.nextInt();

                    System.out.print("Enter available quantity: ");
                    int availableQty = sc.nextInt();

                    Book newBook = new Book(0, title, author, totalQty, availableQty);
                    if (libraryDAO.addBook(newBook)) {
                        System.out.println("✅ Book added successfully!");
                    } else {
                        System.out.println("❌ Failed to add book.");
                    }
                    break;

                case 2:
                    List<Book> books = libraryDAO.getAllBooks();
                    if (books.isEmpty()) {
                        System.out.println("No books available in the library.");
                    } else {
                        System.out.println("\nAvailable Books:");
                        System.out.printf("%-5s %-25s %-20s %-10s %-10s\n",
                                "ID", "Title", "Author", "Total", "Available");
                        for (Book b : books) {
                            System.out.printf("%-5d %-25s %-20s %-10d %-10d\n",
                                    b.getBookId(), b.getTitle(), b.getAuthor(),
                                    b.getTotalQuantity(), b.getAvailableQuantity());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter member ID: ");
                    int memberId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter member name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter contact info: ");
                    String contact = sc.nextLine();

                    Member newMember = new Member(memberId, name, contact);
                    if (libraryDAO.addMember(newMember)) {
                        System.out.println("✅ Member added successfully!");
                    } else {
                        System.out.println("❌ Failed to add member.");
                    }
                    break;

                case 4:
                    System.out.print("Enter book ID to delete: ");
                    int deleteBookId = sc.nextInt();
                    if (libraryDAO.deleteBook(deleteBookId)) {
                        System.out.println("✅ Book deleted successfully!");
                    } else {
                        System.out.println("❌ Failed to delete book or book not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter member ID to delete: ");
                    int deleteMemberId = sc.nextInt();
                    if (libraryDAO.deleteMember(deleteMemberId)) {
                        System.out.println("✅ Member deleted successfully!");
                    } else {
                        System.out.println("❌ Failed to delete member or member not found.");
                    }
                    break;

                case 6:
                    System.out.println("👋 Exiting... Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("⚠️ Invalid choice. Please try again.");
            }
        }
    }
}
