import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class Library { // Created a Library Class
    private Map<String, Book> library;

    public Library() { // Constructor
        library = new HashMap<>(); // Assign new empty HashMap to library variable
    }

    public void addBook(String BookTitle, String BookAuthor, int quantity) { // Function to add book to the library
        if (library.containsKey(BookTitle)) { // Checks if book is available or not
            Book existingBook = library.get(BookTitle);
            existingBook.setQuantity(existingBook.getQuantity() + quantity); // Updating existing book quantity
            System.out.println("Bood Already Exists, Quantity Updated.");
        } else {
            library.put(BookTitle, new Book(BookTitle, BookAuthor, quantity)); // Adds new Book
            System.out.println("Successfully Book added.");
        }
    }

    public void borrowBook(String BookTitle, int bookQuantityToBorrow) { // Function to Borrow book
        if (library.containsKey(BookTitle)) { // Checking if book is available or not
            Book book = library.get(BookTitle);
            int availableBookQuantity = book.getQuantity();
            System.out.println(availableBookQuantity);
            int borrowedBookQuantity = book.getborrowedBookQuantity();
            if (availableBookQuantity >= bookQuantityToBorrow) { // Checking borrow book quantity is available or not
                book.setQuantity(availableBookQuantity - bookQuantityToBorrow); // Updating book quantity
                book.setborrowedBookQuantity(borrowedBookQuantity + bookQuantityToBorrow); // Updating Borrow book
                                                                                           // quantity
                System.out.println(bookQuantityToBorrow + " copies of '" + BookTitle + "' borrowed successfully.");
            } else {
                System.out
                        .println("Sorry! Only " + availableBookQuantity + " '" + BookTitle + "' Books are available.");
            }
        } else {
            System.out.println("Sorry! Your Requested '" + BookTitle + "' book is not available.");
        }
    }

    public void returnBook(String BookTitle, int quantityOfBookToReturn) { // Function to return Book
        if (library.containsKey(BookTitle)) { // Checks if return book available in library or not
            Book book = library.get(BookTitle);
            int bookQuantity = book.getQuantity();
            int borrowedBookQuantity = book.getborrowedBookQuantity();
            if (borrowedBookQuantity >= quantityOfBookToReturn) { // Checking if borrowed book quantity is greater or
                                                                  // equall to return quantity book
                book.setQuantity(bookQuantity + quantityOfBookToReturn); // Updating book quantity
                book.setborrowedBookQuantity(borrowedBookQuantity - quantityOfBookToReturn); // Updating Borrow book
                System.out.println(quantityOfBookToReturn + " '" + BookTitle + "' Books returned successfully.");
            } else {
                System.out.println("This Quantity is more than you borrowed. Please Enter correct number.");
            }
        } else {
            System.out.println("'" + BookTitle + "' is not available in the Library.");
        }
    }

    public void displayBooks() { // Function to display Books
        if (library.size() == 0) {
            System.out.println("No Books Available in Library:");
        } else {
            System.out.println("Available Books: ");
            for (Book book : library.values()) {
                System.out.println(book);
            }
        }
    }

    public static void main(String[] args) {
        Library library = new Library(); // Create Library Object
        CustomFunctions fn = new CustomFunctions(); // Create CustomFunctions Object
        try { // handling Errors
            Scanner sc = new Scanner(System.in);
            while (true) { // Runs untill user want to exit
                System.out.println(
                        "\n\t\t\t#####**************************     1: Display Books     **************************#####");
                System.out.println(
                        "\t\t\t#####**************************     2: Add Book          **************************#####");
                System.out.println(
                        "\t\t\t#####**************************     3: Borrow Book       **************************#####");
                System.out.println(
                        "\t\t\t#####**************************     4: Return Book       **************************#####");
                System.out.println(
                        "\t\t\t#####**************************     5: Exit              **************************#####");
                char choice = sc.next().charAt(0);

                switch (choice) {
                    case '1':
                        library.displayBooks();
                        break;
                    case '2':
                        library.addBook(fn.getBookTitleFromUser(), fn.getAuthorNameFromUser(),
                                fn.getQuantityFromUser());
                        break;
                    case '3':
                        library.borrowBook(fn.getBookTitleFromUser(), fn.getQuantityFromUser());
                        break;
                    case '4':
                        library.returnBook(fn.getBookTitleFromUser(), fn.getQuantityFromUser());
                        break;
                    case '5':
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Input!!!");
                        break;
                }
            }
        } catch (Exception e) {
            throw new Error("Error : " + e.getMessage());
        }
    }
}
