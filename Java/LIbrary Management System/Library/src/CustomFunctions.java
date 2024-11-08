import java.util.Scanner;

class CustomFunctions { // CustomFunctions Class
    public String getBookTitleFromUser() {
        Scanner scanner = new Scanner(System.in); // Function to get book title from user and return it
        try {
            System.out.println("\n\n\t\t\t Enter Book Title : ");

            return scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "";
        }
    }

    public String getAuthorNameFromUser() { // Function to get book Author name from user and return it
        try {
            System.out.println("\n\n\t\t\t Enter Author Name : ");
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "";
        }
    }

    public int getQuantityFromUser() { // Function to get book Quantity from user and return
        try {
            System.out.println("\n\n\t\t\t Enter Book Quantity : ");
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }
}