import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest {
    public static void main(String[] args) {
        // Create a single account with initial balance
        Account account = new Account(1000);

        // Create a list of transactions
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(account, true, 200));   // Deposit
        transactions.add(new Transaction(account, false, 300)); // Withdraw
        transactions.add(new Transaction(account, true, 500));  // Deposit
        transactions.add(new Transaction(account, false, 100)); // Withdraw
        transactions.add(new Transaction(account, true, 150));  // Deposit

        // Create an ExecutorService to manage threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Execute all transactions
        for (Transaction transaction : transactions) {
            executor.execute(transaction);
        }

        // Shutdown the executor after all tasks complete
        executor.shutdown();

        // Wait for all threads to complete
        while (!executor.isTerminated()) {
            Thread.yield();
        }

        // Print final balance
        System.out.println("Final Account Balance: $" + account.getBalance());
    }
}
