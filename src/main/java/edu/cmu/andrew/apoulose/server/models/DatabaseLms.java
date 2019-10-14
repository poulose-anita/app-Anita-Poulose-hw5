package edu.cmu.andrew.apoulose.server.models;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

//@Path("/start")   //
public class DatabaseLms {

    public static MongoDatabase db;


    //@GET
    //@Produces({MediaType.TEXT_PLAIN})

    //Database Connections
    public String establishDbConnection() {
        String connection;
        //if(id.equals("start")||id.equals("reset")) {
        String dbName = "app-apoulose";
        MongoClient mongoCli = new MongoClient();
        db = mongoCli.getDatabase(dbName);
        Library libOper = new Library();

        boolean collectionCreate = db.listCollectionNames().into(new ArrayList<String>()).isEmpty();
        if (collectionCreate) {
            db.createCollection("book");
            db.createCollection("borrower");
            libOper.addBaseData();
            connection = "Lms is Started with base data loaded";
        }
        else {
            connection = "The database is already started with the base data";
        }

        return connection;
    }

    //Insert Books
    void collectionInsert(String collectionName, Book book) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        Document document = new Document("id", book.getBookId().toString()).append("name", book.getBookTitle().toString()).append("author", book.getBookAuthor().toString())
                .append("checkout", "N".toString()).append("borrowerId", "".toString());
        collection.insertOne(document);
    }

    //Insert Borrower
    void collectionInsert(String collectionName, Borrower borrower) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        Document document = new Document("id", borrower.getBorrowerId().toString()).append("name", borrower.getBorrowerName().toString()).append("phone", borrower.getBorrowerPhone().toString());
        collection.insertOne(document);
    }

    //Drop Collections
    public void collectionsDrop(String collectionName){
        String dbName = "app-apoulose";
        MongoClient mongoCli = new MongoClient();
        db = mongoCli.getDatabase(dbName);
        MongoCollection<Document> collection = db.getCollection(collectionName);
        collection.drop();
    }

    void collectionUpdate( String collectionName, Book book){
        MongoCollection<Document> collection = db.getCollection(collectionName);
        collection.updateOne(Filters.eq("id",book.getBookId().toString()), Updates.set("checkout", book.getBookCheckOut()?"Y":"N"));
        collection.updateOne(Filters.eq("id", book.getBookId().toString()), Updates.set("borrowerId", book.getBorrowerId().toString()));
    }

    public ArrayList<BookDb> getAllBooks(String collectionName) {

        ArrayList<BookDb> bookList = new ArrayList<BookDb>();
        String dbName = "app-apoulose";
        MongoClient mongoCli = new MongoClient();
        db = mongoCli.getDatabase(dbName);
        MongoCollection<Document> collection = db.getCollection("book");
        FindIterable<Document> results = collection.find();
        if (results == null) {
            return  bookList;
        }

        for (Document item : results) {

            String borrowerName = getRetrieveBorrowerName(item.getString("borrowerId"));
            BookDb book = new BookDb( item.getString("id"),
                    item.getString("name"),
                    item.getString("author"),
                    item.getString("checkout"),
                    item.getString("borrowerId"),
                    borrowerName
            );
            //    book.setId(item.getObjectId("_id").toString());
            bookList.add(book);
        }

        return bookList;
    }

    // Retrieve Borrower Name
    public String getRetrieveBorrowerName(String id){
        String dbName = "app-apoulose";
        MongoClient mongoCli = new MongoClient();
        db = mongoCli.getDatabase(dbName);
        MongoCollection<Document> collection = db.getCollection("borrower");
        String value="";
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("id", id);
        FindIterable<Document> iterDoc = collection.find(whereQuery);
        for(Document doc : iterDoc) {
            for(Map.Entry<String, Object> entry : doc.entrySet()) {
                if (entry.getKey().equals("name")) {
                    value = (String) entry.getValue();
                }
            }
        }
        return value;
    }


    Book getRetrieveBookDetails(String id){
        MongoCollection<Document> collection = db.getCollection("book");
        String title = "";
        String borrowerId ="" ;
        String author = "";
        boolean bookCheckout =false;
        Book book = new Book ();
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("id", id);
        FindIterable<Document> iterDoc = collection.find(whereQuery);
        for(Document doc : iterDoc) {
            for(Map.Entry<String, Object> entry : doc.entrySet()) {
                if (entry.getKey().equals("name")) {

                    title = (String) entry.getValue();
                }

                if (entry.getKey().equals("author")) {
                    author= (String) entry.getValue();
                }
                if (entry.getKey().equals("borrowerId")) {
                    borrowerId= (String) entry.getValue();
                }
                if (entry.getKey().equals("checkout")) {
                    if(entry.getValue().equals("N")) bookCheckout = false ;
                    if(entry.getValue().equals("Y")) bookCheckout = true ;
                }
            }
        }
        //void setBook(String id, String title, String author,boolean bookCheckout , String borrowerId)
        book.setBook(id,title,author,bookCheckout,borrowerId);
        return book;
    }
}





