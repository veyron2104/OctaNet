import java.util.Scanner;

public class ATMSimulator {
    private double balance;
    int pin=1234;
    private final double[] transactionHistory;
    private int transactionCount;

    // Constructor
    public ATMSimulator() {
        this.balance = 0.0;
        this.transactionHistory = new double[100]; // Fixed-size array for transaction history
        this.transactionCount = 0;
    }

    //Menu
    public void Menu()
    {
        System.out.println("\nATM Menu:");
        System.out.println("1.Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3.Deposit");
        System.out.println("4.Change Pin");
        System.out.println("5.Transaction History");
        System.out.print("Choose an option: ");
    }

    //Check pin
    public void checkpin(){
        System.err.print("Enter your pin:");
        Scanner sc=new Scanner(System.in);
        int enteredpin=sc.nextInt();
        if(enteredpin==pin){
           Menu();
        }
        else{
            System.err.println("Pin is incorret");
            changepin();
            
        }  
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            recordTransaction(amount);
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recordTransaction(-amount);
            System.out.println("Withdrew:" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    // Method to record a transaction
    private void recordTransaction(double amount) {
        if (transactionCount < transactionHistory.length) {
            transactionHistory[transactionCount++] = amount;
        } else {
            System.out.println("Transaction history is full.");
        }
    }

    // Method to display transaction history
    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (int i = 0; i < transactionCount; i++) {
            System.out.println((i + 1) + ": " + (transactionHistory[i] >= 0 ? "Deposited: " : "Withdrew: ") + Math.abs(transactionHistory[i]));
        }
    }

    // Method to check balance
    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }
    
    // Change pin
    public int changepin(){
      Scanner sc=new Scanner(System.in);
        System.err.println("Enter new pin");
        int newpin=sc.nextInt();
        pin=newpin;
        checkpin();
        return pin;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMSimulator atm = new ATMSimulator();
        boolean running = true;
        atm.checkpin();

        while (running) {
            

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    atm.withdraw(withdrawalAmount);
                    break;
                case 3:
                System.out.print("Enter deposit amount: ");
                double depositAmount = scanner.nextDouble();
                atm.deposit(depositAmount);
                    break;
                case 4:
                    atm.changepin();
                    break;
                case 5:
                atm.displayTransactionHistory();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}