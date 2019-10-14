package edu.cmu.andrew.apoulose.server.models;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

//@Path("/books")
public class Library {
    private String borrowerId;
    private DatabaseLms dbOper = new DatabaseLms();

    //Add books to the library directory
    void addBaseData() {

        //Add 10 Books to directory
        for (int i = 0; i < 9; i++) {
            if (i == 0) createBookDir("A234", "The 101 Dalmations", "Dodie Smith");
            if (i == 1) createBookDir("A675", "The Adventures of Huckleberry Finn", "Mark Twain");
            if (i == 2) createBookDir("A212", "Bag of Bones", "Stephen King");
            if (i == 3) createBookDir("B671", "Charlie and the Chocolate Factory", "Roald Dahl");
            if (i == 4) createBookDir("B534", "Charlotte's Web", "E.B.White");
            if (i == 5) createBookDir("B777", "A Christmas Carol", "Charles Dickens");
            if (i == 6) createBookDir("B778", "Dracula", "Bram Stoker");
            if (i == 7) createBookDir("B812", "A Farewell to Arms", "Ernest Hemingway");
            if (i == 8) createBookDir("C101", "The Firm", "John Grisham");

        }

        //Add 5 Borrowers to directory
        for (int i = 0; i < 5; i++) {
            if (i == 0) createBorrowerDir("L34", "Andrea Selleck", "639-555-1239");
            if (i == 1) createBorrowerDir("L22", "Lucas Hyatt", "408-555-2365");
            if (i == 2) createBorrowerDir("L19", "Carol Leonard", "650-555-8921");
            if (i == 3) createBorrowerDir("L84", "Ayesha Ford", "415-555-2120");
            if (i == 4) createBorrowerDir("L77", "Kenneth Trout", "510-555-1982");

        }

    }

    void createBookDir(String id, String title, String author){
        Book book = new Book();
        book.setBook(id,title,author);
        String collectionName = "book";
        dbOper.collectionInsert(collectionName,book);
    }

    //Add borrowers to the library directory
    void createBorrowerDir(String id, String name, String phone){
        Borrower borrower = new Borrower(id, name ,phone);
        //borrower.setBorrower(id,name,phone);
        String collectionName = "borrower";
        dbOper.collectionInsert(collectionName,borrower);
    }


//List all books

  //  @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public ArrayList<BookDb> getBooks() {
        ArrayList<BookDb> bookList = new ArrayList<BookDb>();
        bookList = dbOper.getAllBooks("book");
        return bookList;
    }





}
