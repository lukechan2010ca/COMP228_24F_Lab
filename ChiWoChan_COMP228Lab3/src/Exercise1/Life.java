package Exercise1;

class Life extends Insurance {
    // Constructor
    public Life(String lifeInsurance){
        super("Life Insurance");
    }

    @Override
    public void setInsuranceCost(double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }
    @Override
    public void displayInfo() {
        System.out.println("Insurance Type: " + this.typeOfInsurance);
        System.out.println("Monthly Cost: " + this.monthlyCost);
    }

}
