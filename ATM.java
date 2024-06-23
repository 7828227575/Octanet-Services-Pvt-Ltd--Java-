import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ATM {
    private double balance;
    private String accountNumber;
    private String pin;
    private List<String> transactionHistory;
    private boolean isAuthenticated;

    public ATM(double balance, String accountNumber, String pin) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
        this.isAuthenticated = false;
    }

    public ATM() {
        this(0, null, null);
    }

    public void createAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your account number: ");
        this.accountNumber = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        this.pin = scanner.nextLine();
        System.out.println("Account created successfully!");
    }

    public void authenticate(String accountNumber, String pin) {
        if (accountNumber.equals(this.accountNumber) && pin.equals(this.pin)) {
            this.isAuthenticated = true;
            System.out.println("Authentication successful. You are now logged in.");
        } else {
            System.out.println("Invalid account number or PIN. Please try again.");
        }
    }

    public void displayBalance() {
        if (isAuthenticated) {
            System.out.printf("Your account balance: $%.2f\n", balance);
        } else {
            System.out.println("Please authenticate first.");
        }
    }

    public void deposit(double amount) {
        if (isAuthenticated) {
            if (amount > 0) {
                balance += amount;
                transactionHistory.add(String.format("Deposited $%.2f", amount));
                System.out.printf("Deposited $%.2f\n", amount);
            } else {
                System.out.println("Invalid amount for deposit.");
            }
        } else {
            System.out.println("Please authenticate first.");
        }
    }

    public void withdraw(double amount) {
        if (isAuthenticated) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                transactionHistory.add(String.format("Withdrew $%.2f", amount));
                System.out.printf("Withdrew $%.2f\n", amount);
            } else {
                System.out.println("Invalid amount for withdrawal or insufficient funds.");
            }
        } else {
            System.out.println("Please authenticate first.");
        }
    }

    public void transfer(double amount, ATM recipient) {
        if (isAuthenticated) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                recipient.balance += amount;
                transactionHistory.add(String.format("Transferred $%.2f to %s", amount, recipient.accountNumber));
                System.out.printf("Transferred $%.2f to %s\n", amount, recipient.accountNumber);
            } else {
                System.out.println("Invalid amount for transfer or insufficient funds in your account.");
            }
        } else {
            System.out.println("Please authenticate first.");
        }
    }

    public void showTransactionHistory() {
        if (isAuthenticated) {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Please authenticate first.");
        }
    }

    public static void main(String[] args) {
        ATM account1 = new ATM();
        ATM account2 = new ATM();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Create Account");
            System.out.println("2. Authenticate");
            System.out.println("3. Display Balance");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Transfer");
            System.out.println("7. Transaction History");
            System.out.println("8. Quit");

            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    account1.createAccount();
                    break;
                case "2":
                    System.out.print("Enter your account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter your PIN: ");
                    String pin = scanner.nextLine();
                    account1.authenticate(accountNumber, pin);
                    break;
                case "3":
                    account1.displayBalance();
                    break;
                case "4":
                    System.out.print("Enter the deposit amount: $");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    account1.deposit(depositAmount);
                    break;
                case "5":
                    System.out.print("Enter the withdrawal amount: $");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    account1.withdraw(withdrawAmount);
                    break;
                case "6":
                    System.out.print("Enter the transfer amount: $");
                    double transferAmount = Double.parseDouble(scanner.nextLine());
                    account1.transfer(transferAmount, account2);
                    break;
                case "7":
                    account1.showTransactionHistory();
                    break;
                case "8":
                    System.out.println("Thank you for using our ATM. Have a good day ahead!");
                    return;
                default:
                    System.out.println("Invalid option selected. Please select a valid option.");
                    break;
            }
        }
    }
}
