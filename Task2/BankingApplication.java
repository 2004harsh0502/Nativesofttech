import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private String accountHolder;
    private double balance;

    public Account(int accountNumber, String accountHolder, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.println("Deposited $" + amount + ". New balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }
        balance -= amount;
        System.out.println("Withdrew $" + amount + ". New balance: $" + balance);
    }
    
    public void displayInfo() {
        System.out.println("----- Account Information -----");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + balance);
    }
}

public class BankingApplication {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextAccountNumber = 1001; // Starting account number

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createAccount();
                    break;
                case "2":
                    deposit();
                    break;
                case "3":
                    withdraw();
                    break;
                case "4":
                    checkBalance();
                    break;
                case "5":
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n====== Banking Application Menu ======");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Check Balance");
        System.out.println("5. Exit");
    }
    
    private static void createAccount() {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        double initialDeposit = 0;
        while (true) {
            System.out.print("Enter initial deposit amount: ");
            try {
                initialDeposit = Double.parseDouble(scanner.nextLine());
                if (initialDeposit < 0) {
                    System.out.println("Initial deposit cannot be negative.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a numeric value.");
            }
        }
        Account newAccount = new Account(nextAccountNumber, name, initialDeposit);
        accounts.add(newAccount);
        System.out.println("Account created successfully! Your account number is " + nextAccountNumber);
        nextAccountNumber++;
    }
    
    private static Account findAccount() {
        System.out.print("Enter account number: ");
        int accNum;
        try {
            accNum = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid account number.");
            return null;
        }
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNum) {
                return acc;
            }
        }
        System.out.println("Account not found.");
        return null;
    }
    
    private static void deposit() {
        Account acc = findAccount();
        if (acc == null) {
            return;
        }
        double amount = 0;
        while (true) {
            System.out.print("Enter deposit amount: ");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    System.out.println("Amount must be positive.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount.");
            }
        }
        acc.deposit(amount);
    }
    
    private static void withdraw() {
        Account acc = findAccount();
        if (acc == null) {
            return;
        }
        double amount = 0;
        while (true) {
            System.out.print("Enter withdrawal amount: ");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    System.out.println("Amount must be positive.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount.");
            }
        }
        acc.withdraw(amount);
    }
    
    private static void checkBalance() {
        Account acc = findAccount();
        if (acc == null) {
            return;
        }
        System.out.println("Current balance: $" + acc.getBalance());
    }
}