package Exercise3;

public class BusinessMortgage extends Mortgage{

    // Its constructor sets the interest rate to 1% over the current prime rate.
    public BusinessMortgage(int mortgageNumber, String customerName, double mortgageAmount, double primeRate, int term) {
        super(mortgageNumber, customerName, mortgageAmount, term);
        setInterestRate(primeRate);  // Call the method to set the interest rate
    }

    @Override
    public void setInterestRate(double primeRate) {
        this.interestRate = primeRate + 1;;
    }
}
