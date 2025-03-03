import java.util.HashMap;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
            return true;
        } else {
            System.out.println("Insufficient balance or invalid amount.");
            return false;
        }
    }

    public void displayAccountInfo() {
        System.out.println("\nAccount Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
    }
}

class Bank {
    private HashMap<String, BankAccount> accounts = new HashMap<>();

    public void createAccount(String accNumber, String accHolder, double initialDeposit) {
        if (!accounts.containsKey(accNumber)) {
            BankAccount newAccount = new BankAccount(accNumber, accHolder, initialDeposit);
            accounts.put(accNumber, newAccount);
            System.out.println("✅ Account created successfully!");
        } else {
            System.out.println("❌ Account number already exists!");
        }
    }

    public void depositMoney(String accNumber, double amount) {
        if (accounts.containsKey(accNumber)) {
            accounts.get(accNumber).deposit(amount);
        } else {
            System.out.println("❌ Account not found!");
        }
    }

    public void withdrawMoney(String accNumber, double amount) {
        if (accounts.containsKey(accNumber)) {
            accounts.get(accNumber).withdraw(amount);
        } else {
            System.out.println("❌ Account not found!");
        }
    }

    public void transferMoney(String fromAcc, String toAcc, double amount) {
        if (accounts.containsKey(fromAcc) && accounts.containsKey(toAcc)) {
            if (accounts.get(fromAcc).withdraw(amount)) {
                accounts.get(toAcc).deposit(amount);
                System.out.println("✅ Transfer Successful!");
            }
        } else {
            System.out.println("❌ One or both accounts not found!");
        }
    }

    public void displayAccount(String accNumber) {
        if (accounts.containsKey(accNumber)) {
            accounts.get(accNumber).displayAccountInfo();
        } else {
            System.out.println("❌ Account not found!");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n===== Bank Management System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. View Account Details");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accNumber = scanner.nextLine();
                    System.out.print("Enter Account Holder Name: ");
                    String accHolder = scanner.nextLine();
                    System.out.print("Enter Initial Deposit Amount: ₹");
                    double initialDeposit = scanner.nextDouble();
                    bank.createAccount(accNumber, accHolder, initialDeposit);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    String accNoDeposit = scanner.nextLine();
                    System.out.print("Enter Deposit Amount: ₹");
                    double depositAmount = scanner.nextDouble();
                    bank.depositMoney(accNoDeposit, depositAmount);
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    String accNoWithdraw = scanner.nextLine();
                    System.out.print("Enter Withdrawal Amount: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    bank.withdrawMoney(accNoWithdraw, withdrawAmount);
                    break;

                case 4:
                    System.out.print("Enter Sender Account Number: ");
                    String fromAcc = scanner.nextLine();
                    System.out.print("Enter Receiver Account Number: ");
                    String toAcc = scanner.nextLine();
                    System.out.print("Enter Transfer Amount: ₹");
                    double transferAmount = scanner.nextDouble();
                    bank.transferMoney(fromAcc, toAcc, transferAmount);
                    break;

                case 5:
                    System.out.print("Enter Account Number: ");
                    String accInfo = scanner.nextLine();
                    bank.displayAccount(accInfo);
                    break;

                case 6:
                    System.out.println("Thank you for using our bank system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Invalid option. Please try again.");
            }
        }
    }
}
