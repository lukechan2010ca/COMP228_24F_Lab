package Exercise3;

public class PersonalMortgage extends Mortgage{
    //constructor for all the attributes of PersonalMortgage
    public PersonalMortgage(int mortgageNumber, String customerName, double mortgageAmount, double primeRate, int term){
        super(mortgageNumber, customerName, mortgageAmount, term);
        setInterestRate(primeRate);
    }

    @Override
    public void setInterestRate(double primeRate) {
        this.interestRate = primeRate + 2;
    }
}
