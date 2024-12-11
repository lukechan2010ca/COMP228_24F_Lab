public class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    // Synchronized deposit method
    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount +
                    ", New Balance: $" + balance);
        }
    }

    // Synchronized withdraw method
    public synchronized void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount +
                    ", New Balance: $" + balance);
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount");
        }
    }

    public double getBalance() {
        return balance;
    }
}
