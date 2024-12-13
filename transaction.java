import java.util.*;

// Transaction class to represent a single transaction
class Transaction {
    private int id;
    private String type; // "Income" or "Expense"
    private String category;
    private double amount;
    private String date;

    public Transaction(int id, String type, String category, double amount, String date) {
        this.id = id;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    // Override toString() for display
    @Override
    public String toString() {
        return "ID: " + id + ", Type: " + type + ", Category: " + category +
                ", Amount: " + amount + ", Date: " + date;
    }
}

// ExpenseTracker class to manage transactions
class ExpenseTracker {
    private ArrayList<Transaction> transactions;
    private int transactionCounter;

    public ExpenseTracker() {
        transactions = new ArrayList<>();
        transactionCounter = 1; // Start ID counter from 1
    }

    // Method to add a transaction
    public void addTransaction(String type, String category, double amount, String date) {
        Transaction newTransaction = new Transaction(transactionCounter++, type, category, amount, date);
        transactions.add(newTransaction);
        System.out.println("Transaction added successfully!");
    }

    // Method to view all transactions
    public void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }

    // Method to delete a transaction by ID
    public void deleteTransaction(int id) {
        boolean found = transactions.removeIf(t -> t.getId() == id);
        if (found) {
            System.out.println("Transaction deleted successfully!");
        } else {
            System.out.println("Transaction with ID " + id + " not found.");
        }
    }

    // Method to calculate and display the balance
    public void calculateBalance() {
        double totalIncome = 0, totalExpense = 0;

        for (Transaction t : transactions) {
            if (t.getType().equalsIgnoreCase("Income")) {
                totalIncome += t.getAmount();
            } else if (t.getType().equalsIgnoreCase("Expense")) {
                totalExpense += t.getAmount();
            }
        }

        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expense: " + totalExpense);
        System.out.println("Net Balance: " + (totalIncome - totalExpense));
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();

        while (true) {
            // Display menu
            System.out.println("\n=== Personal Expense Tracker ===");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. Delete Transaction");
            System.out.println("4. Calculate Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline

            switch (choice) {
                case 1:
                    // Add Transaction
                    System.out.print("Enter type (Income/Expense): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the leftover newline
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    tracker.addTransaction(type, category, amount, date);
                    break;

                case 2:
                    // View Transactions
                    System.out.println("\n=== Transactions ===");
                    tracker.viewTransactions();
                    break;

                case 3:
                    // Delete Transaction
                    System.out.print("Enter transaction ID to delete: ");
                    int id = scanner.nextInt();
                    tracker.deleteTransaction(id);
                    break;

                case 4:
                    // Calculate Balance
                    System.out.println("\n=== Balance Summary ===");
                    tracker.calculateBalance();
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting... Thank you for using the Expense Tracker!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
