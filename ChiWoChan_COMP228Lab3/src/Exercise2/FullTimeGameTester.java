package Exercise2;

class FullTimeGameTester extends GameTester {
    public FullTimeGameTester(String name) {
        super(name, true);
    }
    //override the getSalary method to return $3000
    @Override
    public double getSalary() {
        return 3000;
    }
}