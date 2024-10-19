package Exercise2;

public abstract class GameTester {
    private String name;
    private boolean isFullTime;

    //constructor
    public GameTester(String name, boolean status) {
        this.name = name;
        this.isFullTime = status;
    }

    //abstract method to determine the salary
    public abstract double getSalary();

    // Method to display tester information
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Status: " + (isFullTime ? "Full-Time" : "Part-Time"));
        System.out.println("Salary: $" + getSalary());
    }
}