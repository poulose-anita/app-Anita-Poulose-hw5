package edu.cmu.andrew.apoulose.server.models;

public class Book {

    private String bookId ;
    private String bookTitle;
    private boolean bookCheckout;
    private String checkout;
    private String borrowerId;
    private String bookAuthor;
    private String borrowerName;

    void setBook(String bookId , String bookTitle,String bookAuthor, String checkout,String borrowerId,
                 String borrowerName ){
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.checkout = checkout ;
        this.borrowerId = borrowerId;
        this.borrowerName = borrowerName;
    }

    //Method to assign book attributes such as book id , tile etc.
    void setBook(String id, String title, String author){
        this.bookId = id;
        this.bookTitle = title;
        this.bookAuthor = author;
        this.bookCheckout = false ;
        this.borrowerId = "";
    }

    void setBook(String id, String title, String author,boolean bookCheckout , String borrowerId){
        this.bookId = id;
        this.bookTitle = title;
        this.bookAuthor = author;
        this.bookCheckout = bookCheckout ;
        this.borrowerId = borrowerId;
    }

    //Method to set checkout status and assign Borrower id
    void setBook(String id,  boolean bookCheckout , String borrowerId){
        this.bookId = id;
        this.bookCheckout = bookCheckout ;
        this.borrowerId = borrowerId;
    }

    void setBook(boolean bookCheckout){
        this.bookCheckout = bookCheckout;
    }

    //Method to return Book Id
    String getBookId(){
        return this.bookId ;
    }

    //Method to return Book title
    String getBookTitle(){
        return this.bookTitle ;
    }

    //Method to return Book Author
    String getBookAuthor(){
        return this.bookAuthor ;
    }


    boolean getBookCheckOut(){
        return this.bookCheckout ;
    }

    String getBorrowerId(){
        return this.borrowerId ;
    }

    String getCheckout(){
        return this.checkout ;
    }

    String borrowerName(){
        return this.borrowerName ;
    }
}



