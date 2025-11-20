/**
 * LibrarySimulator.java
 *
 * CSC 111 - Computer Programming I
 * Project Phase #2 - Library Simulation
 * Group Members:
 * - Faisal Gong (ID: 446100913)
 * - Yousef Alshiqari (ID: 446101341)
 * - Sameer Alsawadi (ID: 446109369)
 *
 * GitHub Repository: https://github.com/playv56/CSC111_Library_Final_Project
 */
import java.util.Scanner;

public class LibrarySimulator {

    // ====== Constants ======
    static final int MAX_BOOKS = 5;
    static final double BORROW_FEE = 0.50;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Create Member Objects
        Member m1 = new Member(446100913, "FAISAL", 0);
        Member m2 = new Member(446101341, "YOUSEF", 0);
        Member m3 = new Member(446109369, "SAMEER", 0);

        // Main program loop
        boolean running = true;
        while (running) {
            printWelcome();
            printMainMenu();

            System.out.print("Enter choice: ");
            int choice = readIntSafely(in);

            if (choice == 1) {
                Member selected = selectUserMenu(in, m1, m2, m3);
                
                if (selected != null) {
                    userSession(in, selected); 
                }
            } else if (choice == 2) {
                adminMenu(in);
            } else if (choice == 3) {
                System.out.println("\nGoodbye.");
                running = false;
            } else {
                System.out.println("Invalid choice. Try again.\n");
            }
        }

        in.close();
    }

    // ====== UI Printers ======
    static void printWelcome() {
        System.out.println("========================================");
        System.out.println("\tWelcome to the Library Simulator");
        System.out.println("========================================");
    }

    static void printMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("  1) Select User Account");
        System.out.println("  2) Login as Administrator");
        System.out.println("  3) Exit");
    }

    static void printAdminMenu() {
        System.out.println("\n========================================");
        System.out.println("\tAdministrator Menu");
        System.out.println("========================================");
        System.out.println("  1) View Total Statistics & Operations");
        System.out.println("  2) Exit to Main Menu");
    }

    // ====== Menu Handlers ======
    
    static Member selectUserMenu(Scanner in, Member m1, Member m2, Member m3) {
        System.out.println("\nSelect an account:");
        System.out.printf("  1) ID: %d\tName: %s%n", m1.getId(), m1.getName());
        System.out.printf("  2) ID: %d\tName: %s%n", m2.getId(), m2.getName());
        System.out.printf("  3) ID: %d\tName: %s%n", m3.getId(), m3.getName());
        System.out.println("  0) Back to Main Menu");
        System.out.print("Enter choice: ");
        
        int c = readIntSafely(in);
        
        if (c == 1) return m1;
        if (c == 2) return m2;
        if (c == 3) return m3;
        if (c == 0) return null;

        System.out.println("Invalid selection.\n");
        return null;
    }

    static void userSession(Scanner in, Member member) {
        boolean inSession = true;
        while (inSession) {
            System.out.println("\n----------------------------------------");
            System.out.printf("\tUser Operations - %s%n", member.getName());
            System.out.println("----------------------------------------");
            System.out.println("  1) View Borrowed Books Count");
            System.out.println("  2) Borrow Book (0.50 credit fee)");
            System.out.println("  3) Return Book (no fee)");
            System.out.println("  4) View Session Summary");
            System.out.println("  5) Exit to Main Menu");
            System.out.print("Enter choice: ");
            int op = readIntSafely(in);

            // Call methods on the Member object
            if (op == 1) {
                member.viewBorrowedCount();
            } else if (op == 2) {
                member.borrowOne();
            } else if (op == 3) {
                member.returnOne();
            } else if (op == 4) {
                member.displayStatistics();
            } else if (op == 5) {
                member.reset();
                System.out.println("Exiting to Main Menu...\n");
                inSession = false;
            } else {
                System.out.println("Invalid choice. Try again.\n");
            }
        }
    }

    static void adminMenu(Scanner in) {
        boolean adminOpen = true;
        while (adminOpen) {
            printAdminMenu();
            System.out.print("Enter choice: ");
            int op = readIntSafely(in);
            
            if (op == 1) {
                // View all system-wide statistics (from Member's static variables)
                System.out.println("\n--- Total System Statistics ---");
                System.out.printf("Total Revenue Collected:\t%.2f SAR%n", Member.TotalRevenue);
                System.out.printf("Total Books Borrowed:\t\t%d%n", Member.TotalBorrows);
                System.out.printf("Total Books Returned:\t\t%d%n", Member.TotalReturns);
                System.out.printf("Total View Count Checks:\t%d%n", Member.TotalViewBorrowed);
                System.out.println("--------------------------------");
                
                // Most Frequent Operation logic
                if (Member.TotalBorrows > Member.TotalReturns) {
                    System.out.printf("Most frequent operation: Borrow (%d)%n", Member.TotalBorrows);
                } else if (Member.TotalReturns > Member.TotalBorrows) {
                    System.out.printf("Most frequent operation: Return (%d)%n", Member.TotalReturns);
                } else {
                    System.out.printf("Most frequent operation: Tie (Borrow=%d, Return=%d)%n", Member.TotalBorrows, Member.TotalReturns);
                }
                System.out.println("-------------------------------\n");
            } else if (op == 2) {
                System.out.println("Exiting to Main Menu...\n");
                adminOpen = false;
            } else {
                System.out.println("Invalid choice. Try again.\n");
            }
        }
    }

    // Safe integer read to avoid InputMismatch exceptions
    static int readIntSafely(Scanner in) {
        while (!in.hasNextInt()) {
            String bad = in.next(); // consume invalid token
            System.out.printf("Invalid input '%s'. Enter a number: ", bad);
        }
        return in.nextInt();
    }
}