package Exercise1;

class Health extends Insurance {
    // Constructor
    public Health(String healthInsurance){
        super("Health Insurance");
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
