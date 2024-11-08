import java.util.Scanner;

public class CommonFunctions {
    private static Scanner scan = new Scanner(System.in);

    int getNumberFromUser(String message) {
        System.out.print(message);
        while (!scan.hasNextInt()) {
            System.out.print("Invalid! Please Enter any Number: ");
            scan.nextLine();
          }
          int number = scan.nextInt();
        scan.nextLine(); // Consume the newline character
        return number;
    }

    String getStringFromUser(String message) {
        System.out.print(message);
        try {
            return scan.nextLine();
        } catch (Exception e) {
            System.err.println("Invalid input: ");
            return getStringFromUser(message);
        }
    }
}
