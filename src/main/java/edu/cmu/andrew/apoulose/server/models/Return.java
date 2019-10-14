package edu.cmu.andrew.apoulose.server.models;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//@Path("/return")
public class Return {

    //URL:http://localhost:8080/return?borrowerId=200&bookId=3
   // @GET
   // @Produces({MediaType.TEXT_PLAIN})
    public String returnBook( String borrowerId,
                             String bookId) {
        DatabaseLms dbOper = new DatabaseLms();
        String borrowerName;
        Book returnBook = new Book();
        String message;
        borrowerName = dbOper.getRetrieveBorrowerName(borrowerId);
        if(!borrowerName.equals("")) {

            Book book = dbOper.getRetrieveBookDetails(bookId);
            if(!book.getBookTitle().equals("")){
                if (book.getBookCheckOut()) {
                    book.setBook(bookId,false,"");
                    dbOper.collectionUpdate("book",book);
                    message = borrowerName + " have successfully returned " + book.getBookTitle().toString();
                } else {
                    message = borrowerName + " has not currently checked out "+ book.getBookTitle().toString();
                }
            }

            else {
                message  = "Book with Id " + bookId + " does not exist";
            }
        }
        else
            message = "Borrower with Id " + borrowerId + " does not exist";


        return message;
    }

}

