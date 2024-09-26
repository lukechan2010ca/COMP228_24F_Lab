package Excercise3;

public class exercise3
{
    // Method to add two integers
    public static int add(int a, int b) {
        return a + b; // Sum of two integers
    }

    // Method to add two doubles
    public static double add(double a, double b) {
        return a + b; // Sum of two doubles
    }

    // Method to add an array of integers
    public static int add(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number; // Summing all integers in the array
        }
        return sum;
    }

    // Excercise1.Main method to test the overloaded add methods
    public static void main(String[] args) {
        // Excercise1.Test cases for the add methods
        int a = 3, b = 4;
        double c = 3.45, d = 7.55;
        int[] arrayForAdd = {1, 2, 3, 4, 5}; // Array of integersing elements of the array

        // Display the results
        System.out.printf("Sum of integers: %d%n", add(a,b));
        System.out.printf("Sum of doubles: %.2f%n", add(c,d));
        System.out.printf("Sum of array elements: %d%n", add(arrayForAdd));
    }
}
