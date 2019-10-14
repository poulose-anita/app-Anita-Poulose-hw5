package edu.cmu.andrew.apoulose.server.http.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.client.MongoCollection;
import edu.cmu.andrew.apoulose.server.http.responses.AppResponse;
import edu.cmu.andrew.apoulose.server.managers.StartManager;
import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/start")

public class StartHttpInterface extends HttpInterface {

    private ObjectWriter ow;
    private MongoCollection<Document> userCollection = null;

    public StartHttpInterface() {
        ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public AppResponse loadLms() {

        try {

            String connection = StartManager.getInstance().loadBaseData();

            return new AppResponse(connection);

        } catch (Exception e) {
            throw handleException("Start Lms", e);
        }

    }

}
