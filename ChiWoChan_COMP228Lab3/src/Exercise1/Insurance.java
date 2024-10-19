package Exercise1;

abstract class Insurance {
    public String typeOfInsurance;
    public double monthlyCost;

    //constructor
    public Insurance(String typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
    }

    //get method
    public String getTypeOfInsurance() {
        return typeOfInsurance;
    }
    public double getMonthlyCost() {
        return monthlyCost;
    }

    //abstract method
    public abstract void setInsuranceCost(double monthlyCost);
    public abstract void displayInfo();
}
