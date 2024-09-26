package Excercise1;

import javax.swing.*;
import java.util.Random;

public class Test {

    private String[] questions = {
            "In which year did Sun Microsystems fund an internal corporate research project led by James Gosling, \nwhich resulted in a C++ -based object-oriented programming language that Sun called Java?\n\nA. 1999\nB. 1991\nC. 1998\nD. 1990\n ",
            "Which of the following Java program units defines a group of related objects?\n\nA. Java method\nB. Java\nC. Java Class\nD. Java default constructor\n ",
            "Which of the following typically groups related classes so that they could be imported into programs and reused?\n\nA. Method\nB. Package\nC. IDE\nD. Function\n ",
            "Class variables must be declared as__________.\n\nA. const\nB. final\nC. var\nD. static\n ",
            "Method arguments may be______________________________.\n\nA. only strings.\nB. only variables.\nC. constants, variables, or expressions.\nD. only constants.\n "
    };

    private char[] answers = {'B', 'C', 'B', 'D', 'C'};  // Correct answers
    private int correctCount = 0;
    private int incorrectCount = 0;
    private Random random = new Random();

    // Method to simulate the questions
    public String simulateQuestion(int questionNumber) {
        return questions[questionNumber];
    }

    // Method to interact with the user and handle the entire input-answer-check process
    public void inputAnswer() {
        for (int i = 0; i < questions.length; i++) {
            String userAnswer = JOptionPane.showInputDialog(null, simulateQuestion(i), "Question " + (i + 1), JOptionPane.QUESTION_MESSAGE);
            checkAnswer(userAnswer.charAt(0), i);  // Check the answer
        }
        // Display final results after all questions are answered
        displayResults();
    }


    // Method to check the user's answer
    public void checkAnswer(char userAnswer, int questionNumber) {
        if (userAnswer == answers[questionNumber]) {
            correctCount++;
            generateMessage(true); // Congratulatory message
        } else {
            incorrectCount++;
            generateMessage(false); // Incorrect message
            JOptionPane.showMessageDialog(null, "Correct answer is: " + answers[questionNumber]);
        }
    }

    // Method to generate a random message
    public void generateMessage(boolean isCorrect) {
        int messageIndex = random.nextInt(4); // Random number between 0 and 3
        String message = "";
        if (isCorrect) {
            switch (messageIndex) {
                case 0 -> message = "Excellent!";
                case 1 -> message = "Good!";
                case 2 -> message = "Keep up the good work!";
                case 3 -> message = "Nice work!";
            }
        } else {
            switch (messageIndex) {
                case 0 -> message = "No. Please try again.";
                case 1 -> message = "Wrong. Try once more.";
                case 2 -> message = "Don't give up!";
                case 3 -> message = "No. Keep trying.";
            }
        }
        JOptionPane.showMessageDialog(null, message);
    }

    // Method to display the test results
    public void displayResults() {
        int totalQuestions = questions.length;
        int percentage = (correctCount * 100) / totalQuestions;
        String resultMessage = String.format("Excercise1.Test Completed!\nCorrect answers: %d\nIncorrect answers: %d\nPercentage of the correct answers: %d%%", correctCount, incorrectCount, percentage);
        JOptionPane.showMessageDialog(null, resultMessage);
    }
}