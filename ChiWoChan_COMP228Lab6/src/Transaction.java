public class Transaction implements Runnable { // implements Runnable interface to be executed in a separate thread
    // instance variables
    private final Account account;
    private final String transactionType;
    private final double amount;

    // constructor
    public Transaction(Account account, String transactionType, double amount) {
        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    // run method to execute the transaction
    @Override
    public void run() {
        if ("deposit".equalsIgnoreCase(transactionType)) {
            account.deposit(amount);
        } else if ("withdraw".equalsIgnoreCase(transactionType)) {
            account.withdraw(amount);
        } else {
            System.out.println(Thread.currentThread().getName() + " invalid transaction type: " + transactionType);
        }
    }
}
