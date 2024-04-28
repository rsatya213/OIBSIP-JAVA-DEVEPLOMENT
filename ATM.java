import java.util.*;
class Account {
    private String userId;
    private String pin;
    private double balance;
    private ArrayList<String> transactionHistory;
    public Account(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }
    public String getUserId() {
        return userId;
    }
    public boolean verifyPin(String enteredPin) {
        return pin.equals(enteredPin);
    }
    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount);
    }
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -" + amount);
            return true;
        }
        return false;
    }
    public void addToTransactionHistory(String transaction) {
        transactionHistory.add(transaction);
    }
    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }
}
public class ATM {
    private HashMap<String, Account> accounts;

    public ATM() {
        accounts = new HashMap<>();
    }
    public void addUser(String userId, String pin, double balance) {
        accounts.put(userId, new Account(userId, pin, balance));
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM");
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        Account userAccount = accounts.get(userId);
        if (userAccount != null && userAccount.verifyPin(pin)) {
            System.out.println("Login successful!");
            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. View transaction history");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Quit");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        viewTransactionHistory(userAccount);
                        break;
                    case 2:
                        withdraw(userAccount, scanner);
                        break;
                    case 3:
                        deposit(userAccount, scanner);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid user ID or PIN. Please try again.");
        }
    }
    private void viewTransactionHistory(Account account) {
        System.out.println("\nTransaction History:");
        ArrayList<String> transactions = account.getTransactionHistory();
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }
    private void withdraw(Account account, Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }
    private void deposit(Account account, Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful.");
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.addUser("user1", "1234", 1000.0);
        atm.addUser("user2", "5678", 500.0);
        atm.start();
    }
}