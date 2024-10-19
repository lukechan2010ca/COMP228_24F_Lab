package Exercise2;
import java.util.Scanner;


//Allow the user to choose game tester type and enter the number of hours for the part-time testers.
// ask the user to enter the gametester's name first

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameTester gameTester = null;

        while (true) {
            System.out.println("Enter the name of the game tester: ");
            String name = scanner.next();
            System.out.println("Please choose game tester type (1. Full-time testers/ 2. Part-time testers): ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                gameTester = new FullTimeGameTester(name);
                break;
            } else if (choice == 2) {
                System.out.println("Enter the number of hours for the part-time testers: ");
                double hours = scanner.nextInt();
                gameTester = new PartTimeGameTester(name, hours);
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
        }
        // Display information

        System.out.println("\nGame Tester Information:");
        gameTester.displayInfo(); // Call displayInfo() on the gameTester object

        scanner.close();
    }

}


