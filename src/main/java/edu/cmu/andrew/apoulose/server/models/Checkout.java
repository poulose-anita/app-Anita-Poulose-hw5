package edu.cmu.andrew.apoulose.server.models;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//@Path("/checkout")
public class Checkout {
    //URL:http://localhost:8080/checkout?borrowerid=200&bookId=3
   // @GET
    //@Produces({MediaType.APPLICATION_JSON})
   // @Produces({MediaType.TEXT_PLAIN})
    public String checkoutBook( String borrowerId,
                                String bookId) {
        // Write your DB logic here
        //Borrower br = new Borrower();
        Book checkoutBook = new Book();
        DatabaseLms dbOper = new DatabaseLms();
        String borrowerName = dbOper.getRetrieveBorrowerName(borrowerId);
        String message;
        if(!borrowerName.equals("")) {

            Book book = dbOper.getRetrieveBookDetails(bookId);
            if(!book.getBookTitle().equals("")){
                //System.out.println(book.getBookTitle().toString());
                if (!book.getBookCheckOut()) {
                    book.setBook(bookId,true,borrowerId);
                    dbOper.collectionUpdate("book",book);
                    message  = borrowerName + " have successfully checked out " + book.getBookTitle().toString();
                    //System.out.println(borrowerName + " have successfully checked out " + book.getBookTitle().toString());
                } else {
                    message  = book.getBookTitle().toString() + " is already checked out by someone";
                    //System.out.println(book.getBookTitle().toString() + " is already checked out by someone");
                }
            }

            else {
                message = "Book with Id " + bookId + " does not exist";
                //System.out.println("Book with Id " + id + " does not exist");
            }
        }
        else
            message = "Borrower with Id " + borrowerId+ " does not exist";
        //System.out.println("Borrower with Id " + id + " does not exist");

        return message;
    }
}

