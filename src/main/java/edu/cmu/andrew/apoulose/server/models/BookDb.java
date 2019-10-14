package edu.cmu.andrew.apoulose.server.models;

public class BookDb {
    String borrowerId;
    String checkout;
    String bookAuthor;
    String bookTitle;
    String bookId;
    String borrowerName;

    public BookDb(String bookId , String bookTitle, String bookAuthor, String checkout, String borrowerId,
                  String borrowerName ){
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.checkout = checkout ;
        this.borrowerId = borrowerId;
        this.borrowerName = borrowerName;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public String getCheckout() {
        return checkout;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }
}
