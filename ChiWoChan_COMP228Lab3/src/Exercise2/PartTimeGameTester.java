package Exercise2;

class PartTimeGameTester extends GameTester {
    private double hours;
    public PartTimeGameTester(String name, double hours) {
        super(name, false);
        this.hours = hours;
    }
    //override the getSalary method to return $20 per hour
    @Override
    public double getSalary() {
        return 20 * hours;
    }
}