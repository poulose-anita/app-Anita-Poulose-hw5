package edu.cmu.andrew.apoulose.server.http.interfaces;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.client.MongoCollection;
import edu.cmu.andrew.apoulose.server.managers.BorrowerManager;
import edu.cmu.andrew.apoulose.server.models.Borrower;
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

@Path("/borrowers")

public class BorrowerHttpInterface extends HttpInterface{

    private ObjectWriter ow;
    private MongoCollection<Document> borrowerCollection = null;

    public BorrowerHttpInterface() {
        ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public AppResponse postBorrowers(Object request){

        try{
            JSONObject json = null;
            json = new JSONObject(ow.writeValueAsString(request));

            Borrower newBorrower = new Borrower(
                    json.getString("id"),
                    json.getString("name"),
                    json.getString("phone")

            );
            BorrowerManager.getInstance().createBorrower(newBorrower);
            return new AppResponse("Insert Successful");

        }catch (Exception e){
            throw handleException("POST users", e);
        }

    }



    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public AppResponse getBorrowers(@Context HttpHeaders headers){

        try{
            AppLogger.info("Got an API call");
            ArrayList<Borrower> borrowers = BorrowerManager.getInstance().getBorrowerList();

            if(borrowers != null)
                return new AppResponse(borrowers);
            else
                throw new HttpBadRequestException(0, "Problem with getting users");
        }catch (Exception e){
            throw handleException("GET /borrowers", e);
        }


    }




    @GET
    @Path("/{borrowerId}")
    @Produces({MediaType.APPLICATION_JSON})
    public AppResponse getSingleUser(@Context HttpHeaders headers, @PathParam("borrowerId") String borrowerId){

        try{
            AppLogger.info("Got an API call");
            ArrayList<Borrower> borrowers = BorrowerManager.getInstance().getBorrowerById(borrowerId);

            if(borrowers != null)
                return new AppResponse(borrowers);
            else
                throw new HttpBadRequestException(0, "Problem with getting users");
        }catch (Exception e){
            throw handleException("GET /borrowers/{borrowerId}", e);
        }


    }


    @PATCH
    @Path("/{borrowerId}")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public AppResponse patchUsers(Object request, @PathParam("borrowerId") String borrowerId){

        JSONObject json = null;

        try{
            json = new JSONObject(ow.writeValueAsString(request));
            Borrower borrower = new Borrower(

                    json.getString("id"),
                    json.getString("name"),
                    json.getString("phone")
            );

            BorrowerManager.getInstance().updateBorrower(borrower);

        }catch (Exception e){
            throw handleException("PATCH borrowers/{borrowerId}", e);
        }

        return new AppResponse("Update Successful");
    }




    @DELETE
    @Path("/{borrowerId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public AppResponse deleteUsers(@PathParam("borrowerId") String borrowerId){

        try{
            BorrowerManager.getInstance().deleteBorrower(borrowerId);
            return new AppResponse("Delete Successful");
        }catch (Exception e){
            throw handleException("DELETE borrowers/{borrowerId}", e);
        }

    }


}
