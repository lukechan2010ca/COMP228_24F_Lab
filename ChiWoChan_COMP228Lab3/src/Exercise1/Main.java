package Exercise1;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Insurance[] insurances = new Insurance[2];

        // Input insurance information
        for (int i = 0; i < insurances.length; i++) {
            System.out.println("Enter the type of insurance (Health/Life): ");
            String type = scanner.nextLine();
            System.out.println("Enter the monthly cost: ");
            double cost = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            if (type.equalsIgnoreCase("Health")) {
                insurances[i] = new Health("Health Insurance" );
            } else if (type.equalsIgnoreCase("Life")) {
                insurances[i] = new Life("Life Insurance" );
            } else {
                System.out.println("Invalid insurance type.");
                i--;
                continue; // Invalid insurance type - skip this iteration
            }
            // Set insurance cost (abstract method)
            insurances[i].setInsuranceCost(cost);
        }
        // Polymorphic Display insurance information
        System.out.println("\nInsurance Information:");
        for (Insurance insurance : insurances) {
            insurance.displayInfo();
            System.out.println();
        }
        scanner.close();
    }
}