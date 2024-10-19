package Exercise3;

//CityToronto bank provides mortgages for individuals and businesses up to $300,000. Write a Java application that keeps track of mortgages and computes the total amount owed at any time (mortgage amount + interest).
//
//Design the following classes to implement your application:
//
//Mortgage – an abstract class that implements the MortgageConstants interface. A Mortgage includes a mortgage number, customer name, amount of mortgage, interest rate, and term.
//
//Don’t allow mortgage amounts over $300,000. Force any mortgage term that is not defined in the MortgageConstants interface to a short-term, one year loan. Create a getMortgageInfo method to display all the mortgage data.
//
//MortgageConstants – includes constant values for short-term (one year), medium-term (three years) and long-term (5 years) mortgages. It also contains constants for bank name and the maximum mortgage amount.
//BusinessMortgage – extends Mortgage. Its constructor sets the interest rate to 1% over the current prime rate.
//PersonalMortgage - extends Mortgage. Its constructor sets the interest rate to 2% over the current prime rate.
//ProcessMortgage – a main class that create an array of 3 mortgages. Prompt the user for the current interest rate. Then in a loop prompts the user for a mortgage type and all relevant information for that mortgage. Store the created Mortgage objects in the array. When data entry is complete, display all mortgages.

abstract class Mortgage implements MortgageConstants {
    //mortgage number, customer name, amount of mortgage, interest rate, and term
    private int mortgageNumber;
    private String customerName;
    private double mortgageAmount;
    protected double interestRate; // Should be protected so subclasses can access it
    private int term;

    //constructor
    public Mortgage(int mortgageNumber, String customerName, double mortgageAmount, int term) {
        this.mortgageNumber = mortgageNumber;
        this.customerName = customerName;
        // check if mortgage amount is within the limit
        if (mortgageAmount < 0){
            throw new IllegalArgumentException("Mortgage amount cannot be negative");
        } else if (mortgageAmount > maxMortgageAmount){
            throw new IllegalArgumentException("Mortgage amount cannot be greater than max mortgage amount");
        }
        this.mortgageAmount = mortgageAmount;
        // set term to shortTerm if term is not defined in MortgageConstants
        this.term = (term == shortTerm || term == mediumTerm || term == longTerm) ? term : shortTerm;
    }

    // set abstract method to set interest rate
    public abstract void setInterestRate(double primeRate);

    // a method to show mortgage information
    public String getMortgageInfo() {
        return String.format("Bank: %s \nMortgage Number: %d\nCustomer Name: %s\nMortgage Amount: $%.2f\nInterest Rate: %.2f%%\nTerm: %d years\nTotal Amount Owed: $%.2f",
                bankName, mortgageNumber, customerName, mortgageAmount, interestRate, term, calTotalAmountOwed());
    }

    // Method to calculate total amount owed
    public double calTotalAmountOwed() {
        return mortgageAmount + (mortgageAmount * interestRate / 100) * term;
    }
}

