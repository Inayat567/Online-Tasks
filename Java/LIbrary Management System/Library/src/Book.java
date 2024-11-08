class Book { // Book Class
    private String BookTitle;
    private String BookAuthor;
    private int quantity;
    private int borrowedBookQuantity;

    public Book(String BookTitle, String BookAuthor, int quantity) { // Book Constructor
        this.BookTitle = BookTitle;
        this.BookAuthor = BookAuthor;
        this.quantity = quantity;
    }

    public String getBookTitle() { // Function to return book Book Title
        return BookTitle;
    }

    public String getBookAuthor() { // Function to return book BookAuthor
        return BookAuthor;
    }

    public int getQuantity() { // Function to return book quantity
        return quantity;
    }

    public int getborrowedBookQuantity() { // Function to return borrowed book quantity
        return borrowedBookQuantity;
    }

    public void setQuantity(int BookQuantity) { // set Book quantity
        this.quantity = BookQuantity;
    }

    public void setborrowedBookQuantity(int BorrowedBookQuantity) { // set borrowed Book quantity
        this.borrowedBookQuantity = BorrowedBookQuantity;
    }

    @Override
    public String toString() { // Function to return books detail in same pattern
        return BookTitle + " by " + BookAuthor + " (Quantity: " + quantity + ")";
    }
}