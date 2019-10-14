package edu.cmu.andrew.apoulose.server.managers;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import edu.cmu.andrew.apoulose.server.exceptions.AppInternalServerException;
import edu.cmu.andrew.apoulose.server.exceptions.AppException;
import edu.cmu.andrew.apoulose.server.models.Borrower;
import edu.cmu.andrew.apoulose.server.utils.MongoPool;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import java.lang.String;
import java.util.ArrayList;

public class BorrowerManager extends Manager {
    public static BorrowerManager _self;
    private MongoCollection<Document> borrowerCollection;


    public BorrowerManager() {
        this.borrowerCollection = MongoPool.getInstance().getCollection("borrower");
    }

    public static BorrowerManager getInstance(){
        if (_self == null)
            _self = new BorrowerManager();
        return _self;
    }


    public void createBorrower(Borrower borrower) throws AppException {

        try{
            JSONObject json = new JSONObject(borrower);

            Document newDoc = new Document()
                    .append("id", borrower.getBorrowerId())
                    .append("name", borrower.getBorrowerName())
                    .append("phone",borrower.getBorrowerPhone());
            if (newDoc != null)
                borrowerCollection.insertOne(newDoc);
            else
                throw new AppInternalServerException(0, "Failed to create new user");

        }catch(Exception e){
            throw handleException("Create User", e);
        }

    }

    public void updateBorrower( Borrower borrower) throws AppException {
        try {


            Bson filter = new Document("id", new String(borrower.getBorrowerId()));
            Bson newValue = new Document()
                    .append("id", borrower.getBorrowerId())
                    .append("name", borrower.getBorrowerPhone())
                    .append("phone",borrower.getBorrowerPhone());
            Bson updateOperationDocument = new Document("$set", newValue);

            if (newValue != null)
                borrowerCollection.updateOne(filter, updateOperationDocument);
            else
                throw new AppInternalServerException(0, "Failed to update user details");

        } catch(Exception e) {
            throw handleException("Update Borrower", e);
        }
    }

    public void deleteBorrower(String borrowerId) throws AppException {
        try {
            Bson filter = new Document("id", new String (borrowerId));
            borrowerCollection.deleteOne(filter);
        }catch (Exception e){
            throw handleException("Delete borrower", e);
        }
    }

    public ArrayList<Borrower> getBorrowerList() throws AppException {
        try{
            ArrayList<Borrower> borrowerList = new ArrayList<>();
            FindIterable<Document> borrowerDocs = borrowerCollection.find();
            for(Document borrowerDoc: borrowerDocs) {
                Borrower borrower = new Borrower(
                        borrowerDoc.getString("id"),
                        borrowerDoc.getString("name"),
                        borrowerDoc.getString("phone")
                );
                borrowerList.add(borrower);
            }
            return new ArrayList<>(borrowerList);
        } catch(Exception e){
            throw handleException("Get borrower List", e);
        }
    }

    public ArrayList<Borrower> getBorrowerById(String id) throws AppException {
        try{
            ArrayList<Borrower> borrowerList = new ArrayList<>();
            FindIterable<Document> borrowerDocs = borrowerCollection.find();
            for(Document borrowerDoc: borrowerDocs) {
                if(borrowerDoc.getString("id").toString().equals(id)) {
                    Borrower borrower = new Borrower(
                            borrowerDoc.getString("id"),
                            borrowerDoc.getString("name"),
                            borrowerDoc.getString("phone")
                    );
                    borrowerList.add(borrower);
                }
            }
            return new ArrayList<>(borrowerList);
        } catch(Exception e){
            throw handleException("Get borrower List", e);
        }
    }

}
