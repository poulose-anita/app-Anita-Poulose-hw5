package edu.cmu.andrew.apoulose.server.http.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.client.MongoCollection;
import edu.cmu.andrew.apoulose.server.http.exceptions.HttpBadRequestException;
import edu.cmu.andrew.apoulose.server.http.responses.AppResponse;
import edu.cmu.andrew.apoulose.server.managers.BookManager;
import edu.cmu.andrew.apoulose.server.managers.CheckoutManager;
import edu.cmu.andrew.apoulose.server.models.BookDb;
import edu.cmu.andrew.apoulose.server.utils.AppLogger;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("/checkouts")

public class CheckoutInterface extends HttpInterface {

    private ObjectWriter ow;
    private MongoCollection<Document> userCollection = null;

    public CheckoutInterface() {
        ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public AppResponse checkoutBooks(@QueryParam("borrowerId") String borrowerId,
                                     @QueryParam("bookId") String bookId) {

        try {

            String message = CheckoutManager.getInstance().checkoutBook(borrowerId,bookId);

            return new AppResponse(message);

        } catch (Exception e) {
            throw handleException("Start Lms", e);
        }

    }


    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public AppResponse returnBooks(@QueryParam("borrowerId") String borrowerId,
                                     @QueryParam("bookId") String bookId) {

        try {

            String message = CheckoutManager.getInstance().returnBook(borrowerId,bookId);

            return new AppResponse(message);

        } catch (Exception e) {
            throw handleException("Return Book", e);
        }

    }


    //Get available books
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public AppResponse getBooksCheckout() {

            String checkout = "Y";

        try {
            AppLogger.info("Got an API call");
            ArrayList<BookDb> books = BookManager.getInstance().getBookByCheckoutStatus(checkout);

            if (books != null)
                return new AppResponse(books);
            else
                throw new HttpBadRequestException(0, "Problem with getting available books");
        } catch (Exception e) {
            throw handleException("GET /books", e);
        }


    }

}
