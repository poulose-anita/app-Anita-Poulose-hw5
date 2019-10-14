package edu.cmu.andrew.apoulose.server.managers;

import com.mongodb.client.MongoCollection;
import edu.cmu.andrew.apoulose.server.exceptions.AppException;
import edu.cmu.andrew.apoulose.server.models.DatabaseLms;
import edu.cmu.andrew.apoulose.server.utils.MongoPool;
import org.bson.Document;

public class StartManager extends Manager {
    public static StartManager _self;
    private MongoCollection<Document> bookCollection;
    private MongoCollection<Document> borrowerCollection;
    private DatabaseLms dbOper = new DatabaseLms();
    private String connection;


    public StartManager() {

        this.bookCollection = MongoPool.getInstance().getCollection("book");
        this.borrowerCollection = MongoPool.getInstance().getCollection("borrower");

    }

    public static StartManager getInstance() {
        if (_self == null)
            _self = new StartManager();
        return _self;
    }


    public String loadBaseData() throws AppException {

        try {

           connection = dbOper.establishDbConnection();
           return connection;

        } catch (Exception e) {
            throw handleException("Could not Load the base data", e);
        }

    }

}

