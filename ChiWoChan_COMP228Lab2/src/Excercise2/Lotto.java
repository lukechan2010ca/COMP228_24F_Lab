package Excercise2;

import javax.swing.JOptionPane;
import java.util.Random;

public class Lotto {
    private int[] numbers; // instance variable Array to store 3 random numbers
    private Random random;

    // Constructor to initialize the Lotto3 object
    public Lotto() {
        numbers = new int[3];
        random = new Random();
        populateNumbers();
    }

    // Method to generate 3 random numbers between 1 and 9
    private void populateNumbers() {
        for (int i = 0; i < 3; i++) {
            numbers[i] = random.nextInt(9) + 1;
        }
    }

    // Method to return the array of numbers
    public int[] getNumbers() {
        return numbers;
    }

    // Method to return the sum of the 3 random numbers
    public int getSum() {
        return numbers[0] + numbers[1] + numbers[2];
    }

    // Main method - entry point of the program
    public static void main(String[] args) {
        playLottoGame();
    }

    // Method to run the Lotto game
    private static void playLottoGame() {
        int userChoice = getUserChoice(); // Get the user's number choice

        boolean userWon = false; // Flag to track if the user won

        // The user has 5 chances to match the sum of the random numbers
        for (int i = 0; i < 5; i++) {
            Lotto lotto = new Lotto();
            int sum = lotto.getSum();

            // Format and display the current roll and the sum of the random numbers
            String message = String.format("Roll %d: %d + %d + %d = %d",
                    i + 1, lotto.getNumbers()[0], lotto.getNumbers()[1], lotto.getNumbers()[2], sum);
            JOptionPane.showMessageDialog(null, message);

            // If the sum matches the user's choice, set userWon to true and exit the loop
            if (sum == userChoice) {
                userWon = true;
                break;
            }
        }
        displayResult(userWon);
    }

    // Method to get the user's number choice between 3 and 27
    private static Integer getUserChoice() {
        while (true) {
            // Show an input dialog to the user
            String input = JOptionPane.showInputDialog("Choose a number between 3 and 27:");

            try {
                // Try to parse the input as an integer
                int choice = Integer.parseInt(input);
                // If the number is between 3 and 27, return the choice
                if (choice >= 3 && choice <= 27) {
                    return choice;
                } else {
                    // Show an error if the number is not in the valid range
                    JOptionPane.showMessageDialog(null, "Please enter a number between 3 and 27.");
                }
            } catch (NumberFormatException e) {
                // Show an error if the input is not a valid number
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            }
        }
    }

    // Method to display the result of the game
    private static void displayResult(boolean userWon) {
        // If the user won, show a congratulations message; otherwise, a losing message
        String resultMessage = userWon ? "Congratulations! You won!" : "Sorry, you lost. The computer wins.";
        JOptionPane.showMessageDialog(null, resultMessage);
    }
}
