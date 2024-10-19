package Exercise3;
import java.util.Scanner;

public class ProcessMortgage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Mortgage[] mortgages = new Mortgage[3];

        System.out.println("Enter the current prime interest rate : ");
        double primeRate = sc.nextDouble();

        //For loop to create 3 mortgages
        for(int i = 0; i < mortgages.length; i++) {
            System.out.println("Mortgage " + (i+1) + ":");

            System.out.print("Enter the mortgage type (1 for personal, 2 for business) : ");
            int mortgageType = sc.nextInt();

            System.out.print("Enter mortgage number (1-1000000): ");
            int mortgageNumber = sc.nextInt();
            //error handling for invalid input type(not integer) of mortgage number
            if (mortgageNumber < 1 || mortgageNumber > 1000000) {
                System.out.println("Invalid mortgage number");
                i--;
                continue;
            }

            sc.nextLine();
            System.out.print("Enter customer name: ");
            String customerName = sc.nextLine();
            //error handling for empty customer name
            if (customerName.isEmpty()) {
                System.out.println("Invalid customer name");
                i--;
                continue;
            }

            System.out.print("Enter mortgage amount: ");
            double amount = sc.nextDouble();

            System.out.print("Enter term (1, 3, or 5 years): ");
            int term = sc.nextInt();

            if (mortgageType == 1) {
                mortgages[i] = new PersonalMortgage(mortgageNumber, customerName, amount, primeRate, term);
            } else if (mortgageType == 2) {
                mortgages[i] = new BusinessMortgage(mortgageNumber, customerName, amount, primeRate, term);
            }
            else {
                System.out.println("Invalid mortgage type");
                i--;
            }

        }
        // show the mortgage details
        System.out.println("\nMortgage Details:");
        for(Mortgage mortgage : mortgages) {
            System.out.println("\n" + mortgage.getMortgageInfo());
        }

    }
}
