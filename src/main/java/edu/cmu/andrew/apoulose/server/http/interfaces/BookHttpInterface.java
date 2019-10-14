package edu.cmu.andrew.apoulose.server.http.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.client.MongoCollection;
import edu.cmu.andrew.apoulose.server.managers.BookManager;
import edu.cmu.andrew.apoulose.server.models.BookDb;
import edu.cmu.andrew.apoulose.server.utils.AppLogger;
import edu.cmu.andrew.apoulose.server.http.exceptions.HttpBadRequestException;
import edu.cmu.andrew.apoulose.server.http.responses.AppResponse;
import edu.cmu.andrew.apoulose.server.http.utils.PATCH;
import org.bson.Document;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/books")

public class BookHttpInterface extends HttpInterface {

    private ObjectWriter ow;
    private MongoCollection<Document> userCollection = null;

    public BookHttpInterface() {
        ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public AppResponse postBooks(Object request) {

        try {
            JSONObject json = null;
            json = new JSONObject(ow.writeValueAsString(request));

            BookDb newBook = new BookDb(
                    json.getString("id"),
                    json.getString("name"),
                    json.getString("author"),
                  "N",
                    "",
                    ""
            );

            BookManager.getInstance().createBook(newBook);
            return new AppResponse("Insert Successful");

        } catch (Exception e) {
            throw handleException("POST books", e);
        }

    }

//Get all books
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public AppResponse getBooks(@Context HttpHeaders headers) {

        try {
            AppLogger.info("Got an API call");
            ArrayList<BookDb> books = BookManager.getInstance().getBookList();

            if (books != null)
                return new AppResponse(books);
            else
                throw new HttpBadRequestException(0, "Problem with getting books");
        } catch (Exception e) {
            throw handleException("GET /books", e);
        }


    }

    //Get available books
    @GET
    @Path("/query/")
    @Produces({MediaType.APPLICATION_JSON})
    public AppResponse getBooksAvailable(@QueryParam("available") String checkout) {
             if (checkout.equals("true")){
                 checkout = "N";
             }
             else
             {
                 checkout = "Y";
             }
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


    @GET
    @Path("/{bookId}")
    @Produces({MediaType.APPLICATION_JSON})
    public AppResponse getSingleUser(@Context HttpHeaders headers, @PathParam("bookId") String bookId){

        try{
            AppLogger.info("Got an API call");
            ArrayList<BookDb> books = BookManager.getInstance().getBookById(bookId);

            if(books != null)
                return new AppResponse(books);
            else
                throw new HttpBadRequestException(0, "Problem with getting books");
        }catch (Exception e){
            throw handleException("GET /books/{bookId}", e);
        }


    }


    @PATCH
    @Path("/{bookId}")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public AppResponse patchUsers(Object request, @PathParam("bookId") String borrowerId){

        JSONObject json = null;

        try{
            json = new JSONObject(ow.writeValueAsString(request));
            BookDb book = new BookDb(
                    json.getString("id"),
                    json.getString("name"),
                    json.getString("author"),
                    "N",
                    "",
                    ""
            );

            BookManager.getInstance().updateBook(book);

        }catch (Exception e){
            throw handleException("PATCH books/{bookId}", e);
        }

        return new AppResponse("Update Successful");
    }




    @DELETE
    @Path("/{bookId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public AppResponse deleteUsers(@PathParam("bookId") String borrowerId){

        try{
            BookManager.getInstance().deleteBook(borrowerId);
            return new AppResponse("Delete Successful");
        }catch (Exception e){
            throw handleException("DELETE books/{bookId}", e);
        }

    }
}