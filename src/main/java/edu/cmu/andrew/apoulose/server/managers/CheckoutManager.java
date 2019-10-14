package edu.cmu.andrew.apoulose.server.managers;


import com.mongodb.client.MongoCollection;
import edu.cmu.andrew.apoulose.server.exceptions.AppException;
import edu.cmu.andrew.apoulose.server.models.Checkout;
import edu.cmu.andrew.apoulose.server.models.Return;
import edu.cmu.andrew.apoulose.server.utils.MongoPool;
import org.bson.Document;

public class CheckoutManager extends Manager {
    public static CheckoutManager _self;
    private MongoCollection<Document> bookCollection;
    private MongoCollection<Document> borrowerCollection;
    private Checkout checkout = new Checkout();
    private Return bookReturn = new Return();
    private String message;


    public CheckoutManager() {

        this.bookCollection = MongoPool.getInstance().getCollection("book");
        this.borrowerCollection = MongoPool.getInstance().getCollection("borrower");

    }

    public static CheckoutManager getInstance() {
        if (_self == null)
            _self = new CheckoutManager();
        return _self;
    }


    public String checkoutBook(String borrowerId,String bookId) throws AppException {

        try {

            message = checkout.checkoutBook(borrowerId,bookId);
            return message;

        } catch (Exception e) {
            throw handleException("Could not Load the base data", e);
        }

    }


    public String returnBook(String borrowerId,String bookId) throws AppException {

        try {

            message = bookReturn.returnBook(borrowerId,bookId);
            return message;

        } catch (Exception e) {
            throw handleException("Could not Load the base data", e);
        }

    }


}


