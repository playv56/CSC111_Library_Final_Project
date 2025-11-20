/**
 * Member.java
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

public class Member {
    // === Private Instance Attributes (Per Member) ===
    private int id;                 
    private String name;            
    private int borrowedCount;      // Persistent count
    private int numViewBorrowed;    // Session statistic
    private int numBorrows;         // Session statistic
    private int numReturns;         // Session statistic
    private double sessionFees;     // Session statistic
    
    // === Public Static Attributes (System-wide) ===
    public static double TotalRevenue = 0.0;        
    public static int TotalViewBorrowed = 0;        
    public static int TotalBorrows = 0;             
    public static int TotalReturns = 0;             

    // === Constants ===
    private static final int MAX_BOOKS = 5;
    private static final double BORROW_FEE = 0.50;

    // === Constructor ===
    public Member(int id, String name, int borrowedCount) {
        this.id = id;
        this.name = name;
        this.borrowedCount = borrowedCount;
        
        // Initialize session stats
        this.numViewBorrowed = 0;
        this.numBorrows = 0;
        this.numReturns = 0;
        this.sessionFees = 0.0;
    }

    // === Private Helpers ===
    private boolean canBorrow() {
        return borrowedCount < MAX_BOOKS; 
    }

    private boolean canReturn() {
        return borrowedCount > 0; 
    }

    // === Public Methods ===

    public void viewBorrowedCount() { 
        this.numViewBorrowed++;
        TotalViewBorrowed++;
        System.out.println(this.name + " currently has " + this.borrowedCount + " borrowed book(s).");
    }

    public boolean borrowOne() { 
        if (canBorrow()) {
            this.borrowedCount++;
            this.numBorrows++;
            TotalBorrows++;
            this.sessionFees += BORROW_FEE;
            TotalRevenue += BORROW_FEE; // Charge fee
            System.out.printf("Borrowed 1 book. Current borrowed: %d\tFee: %.2f credit%n", this.borrowedCount, BORROW_FEE);
            return true;
        } else {
            System.out.printf("Error: You cannot borrow more than %d books.%n", MAX_BOOKS);
            return false;
        }
    }

    public boolean returnOne() { 
        if (canReturn()) {
            this.borrowedCount--;
            this.numReturns++;
            TotalReturns++;
            System.out.printf("Returned 1 book. Current borrowed: %d%n", this.borrowedCount);
            return true;
        } else {
            System.out.println("Error: No books to return.");
            return false;
        }
    }

    public void displayStatistics() { 
        System.out.println("\n------ Session Summary ------");
        System.out.printf("User: %s (ID: %d)%n", this.name, this.id);
        System.out.printf("Books borrowed this session:\t%d%n", this.numBorrows);
        System.out.printf("Books returned this session:\t%d%n", this.numReturns);
        System.out.printf("Viewed borrowed count:\t%d%n", this.numViewBorrowed);
        System.out.printf("Total fees this session:\t%.2f%n", this.sessionFees);
        System.out.println("-----------------------------\n");
    }

    public void reset() { // Resets session statistics
        this.numViewBorrowed = 0;
        this.numBorrows = 0;
        this.numReturns = 0;
        this.sessionFees = 0.0;
    }

    // === Getters ===
    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public int getBorrowedCount() { return this.borrowedCount; }
}