import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest {
    public static void main(String[] args) {
        // // Create a single account with initial balance 1000
        Account account = new Account(1000);

        // Create a list to hold transactions
        List<Transaction> transactions = new ArrayList<>();

        // Add transactions to the list
        transactions.add(new Transaction(account, "deposit", 200));
        transactions.add(new Transaction(account, "withdraw", 50));
        transactions.add(new Transaction(account, "withdraw", 500));
        transactions.add(new Transaction(account, "deposit", 300));
        transactions.add(new Transaction(account, "withdraw", 100));

        // Create an ExecutorService with a fixed thread pool size of 5
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Execute each transaction in the list
        for (Transaction transaction : transactions) {
            executor.execute(transaction);
        }

        // Shut down the executor when all tasks are complete
        executor.shutdown();

        // let the executor finish all tasks
        while (!executor.isTerminated()) {
            Thread.yield();
        }

        // Print final account balance
        System.out.println("Account Balance: $" + account.getBalance());
    }
}
