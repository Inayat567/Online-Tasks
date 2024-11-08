import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception { // main class of the program (Entry Point)
        Scanner scanner = new Scanner(System.in); // Make a Scanner Instance to get user input
        int score = 0; // Variable to track the score

        // Questions of the quiz game
        final String[] questions = {
            "1. What is the keyword used to declare a constant in Java?",
            "2. Which data type is used to store whole numbers in Java?",
            "3. What is the main purpose of the 'if' statement in Java?",
            "4. Which Java keyword is used to create a new instance of a class?",
            "5. What is the output of 'System.out.println(5 + 3 + \"Java\")'?"
        };

        // Option of the above questions
        final String[][] options = {
            { "A. final", "B. const", "C. static", "D. int" },
            { "A. float", "B. double", "C. int", "D. string" },
            { "A. Looping", "B. Branching", "C. Data storage", "D. Error handling" },
            { "A. new", "B. class", "C. this", "D. instance" },
            { "A. 8Java", "B. 5 + 3Java", "C. 8Java", "D. 53Java" }
        };
        /*
         * These are the Correct Options of the questions
         * final keyword is used to make the variable constant
         */
        final char[] correctOptions = { 'A', 'C', 'B', 'A', 'D' };

        // Display each questions and process the quiz game untill all the questions
        // ends
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            // this loop is used to display the option of the questions
            for (int j = 0; j < 4; j++) {
                System.out.println(options[i][j]);
            }
            // This show the user to select the option i.e. A, B, C or D
            System.out.print("Enter your answer (A, B, C, or D): ");
            // This line get the user selected option (character)
            char userSelectedOption = Character.toUpperCase(scanner.next().charAt(0));
            /*
             * Here is the switch statement which has four cases for four options and if
             * user enter
             * character other than the allowed charter, we decrement the i variable to
             * track the
             * question. In each case we are checking the user selected option is correct or
             * not.
             * If user's selected Option is correct, score variable will increment and break
             * the statement.
             */
            switch (userSelectedOption) {
                case 'A':
                    if (userSelectedOption == correctOptions[i])
                        score++;
                    break;
                case 'B':
                    if (userSelectedOption == correctOptions[i])
                        score++;
                    break;
                case 'C':
                    if (userSelectedOption == correctOptions[i])
                        score++;
                    break;
                case 'D':
                    if (userSelectedOption == correctOptions[i])
                        score++;
                    break;
                default:
                    System.out.print("\n***\n\nInvalid Option! \nValid Options are: A, B, C or D\n\n***\n");
                    // Decrementing the i value by one so that it can start from next question again
                    i--;
                    break;
            }
        }

        /*
         * This line calculate the result in percentage.
         * (double) is used to convert it to double
         * If we does not use it, then we get wrong result e.g.
         * score = 3, the 3/5 will equall to 0.
         * When we use (double) It will equall to 0.6 then 0.6 * 100 == 60
         */
        double Finalscore = (double) score / questions.length * 100;
        System.out.println("Quiz completed! Your score: " + Finalscore + "%");

        scanner.close(); // finallry, closed Scanner instance
    }
}
