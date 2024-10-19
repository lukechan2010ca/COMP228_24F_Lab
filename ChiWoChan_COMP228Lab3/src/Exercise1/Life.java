package Exercise1;

class Life extends Insurance {

    public Life(String lifeInsurance){
        super("Life Insurance");
    }

    public void setInsuranceCost(double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public void displayInfo() {
        System.out.println("Insurance Type: " + this.typeOfInsurance);
        System.out.println("Monthly Cost: " + this.monthlyCost);
    }

}
