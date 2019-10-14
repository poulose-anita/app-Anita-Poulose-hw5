package edu.cmu.andrew.apoulose.server.managers;


import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import edu.cmu.andrew.apoulose.server.exceptions.AppInternalServerException;
import edu.cmu.andrew.apoulose.server.exceptions.AppException;
import edu.cmu.andrew.apoulose.server.models.BookDb;
import edu.cmu.andrew.apoulose.server.models.DatabaseLms;
import edu.cmu.andrew.apoulose.server.utils.MongoPool;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import java.lang.String;
import java.util.ArrayList;

public class BookManager extends Manager {
    public static BookManager _self;
    private MongoCollection<Document> bookCollection;
    private DatabaseLms dbOper = new DatabaseLms();


    public BookManager() {
        this.bookCollection = MongoPool.getInstance().getCollection("book");
    }

    public static BookManager getInstance() {
        if (_self == null)
            _self = new BookManager();
        return _self;
    }

//Add a new book
    public void createBook(BookDb book) throws AppException {

        try {
            JSONObject json = new JSONObject(book);

            Document newDoc = new Document()
                    .append("id", book.getBookId())
                    .append("name", book.getBookTitle())
                    .append("author", book.getBookAuthor())
                    .append("checkout", book.getCheckout())
                    .append("borrowerId", book.getBorrowerId());
            if (newDoc != null)
                bookCollection.insertOne(newDoc);
            else
                throw new AppInternalServerException(0, "Failed to create new user");

        } catch (Exception e) {
            throw handleException("Create User", e);
        }

    }


//List all books
    public ArrayList<BookDb> getBookList() throws AppException {
        try{

            ArrayList<BookDb> bookList = new ArrayList<>();
             bookList = dbOper.getAllBooks("book");

           /* ArrayList<User> userList = new ArrayList<>();
            FindIterable<Document> userDocs = userCollection.find();
            for(Document userDoc: userDocs) {
                User user = new User(
                        userDoc.getObjectId("_id").toString(),
                        userDoc.getString("username"),
                        userDoc.getString("password"),
                        userDoc.getString("email")
                );
                userList.add(user);
            }*/
            return bookList;
        } catch(Exception e){
            throw handleException("Get User List", e);
        }
    }

  //Get the book for a book Id
    public ArrayList<BookDb> getBookById(String id) throws AppException {
        try{
            ArrayList<BookDb> bookList = new ArrayList<>();
            FindIterable<Document> bookDocs = bookCollection.find();
            for(Document bookDoc: bookDocs) {
                if(bookDoc.getString("id").toString().equals(id)) {
                    String borrowerName = dbOper.getRetrieveBorrowerName(bookDoc.getString("borrowerId").toString());
                    BookDb book = new BookDb(
                            //userDoc.getString("id").toString(),
                            bookDoc.getString("id"),
                            bookDoc.getString("name"),
                            bookDoc.getString("author"),
                            bookDoc.getString("checkout"),
                            bookDoc.getString("borrowerId"),
                           borrowerName
                    );
                    bookList.add(book);
                }
            }
            return new ArrayList<>(bookList);
        } catch(Exception e){
            throw handleException("Get book by book id", e);
        }
    }

    public void updateBook( BookDb book) throws AppException {
        try {


            Bson filter = new Document("id", new String(book.getBookId()));
            Bson newValue = new Document()
                    .append("id", book.getBookId())
                    .append("name", book.getBookTitle())
                    .append("author",book.getBookAuthor());
            Bson updateOperationDocument = new Document("$set", newValue);

            if (newValue != null)
                bookCollection.updateOne(filter, updateOperationDocument);
            else
                throw new AppInternalServerException(0, "Failed to update user details");

        } catch(Exception e) {
            throw handleException("Update Borrower", e);
        }
    }

    public void deleteBook(String bookId) throws AppException {
        try {
            Bson filter = new Document("id", new String (bookId));
            bookCollection.deleteOne(filter);
        }catch (Exception e){
            throw handleException("Delete borrower", e);
        }
    }


    public ArrayList<BookDb> getBookByCheckoutStatus(String checkout) throws AppException {
        try{
            ArrayList<BookDb> bookList = new ArrayList<>();
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("checkout", checkout);
            FindIterable<Document> bookDocs = bookCollection.find(whereQuery);
            for(Document bookDoc: bookDocs) {
              //  if(bookDoc.getString("id").toString().equals(id)) {
                    String borrowerName = dbOper.getRetrieveBorrowerName(bookDoc.getString("borrowerId").toString());
                    BookDb book = new BookDb(
                            //userDoc.getString("id").toString(),
                            bookDoc.getString("id"),
                            bookDoc.getString("name"),
                            bookDoc.getString("author"),
                            bookDoc.getString("checkout"),
                            bookDoc.getString("borrowerId"),
                            borrowerName
                    );
                    bookList.add(book);
               // }
            }
            return new ArrayList<>(bookList);
        } catch(Exception e){
            throw handleException("Get book by book id", e);
        }
    }

}
