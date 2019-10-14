package edu.cmu.andrew.apoulose.server.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.logging.Level;
import java.util.logging.Logger;


public class MongoPool {

    private static MongoPool mp;
    private static MongoDatabase db;

    private MongoPool() {
        try {
            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
            //build the connection options
            builder.maxConnectionIdleTime(60000);//set the max wait time in (ms)
            builder.socketKeepAlive(true);
            builder.connectTimeout(30000);
            MongoClientOptions opts = builder.build();

            MongoClient mc = new MongoClient(new ServerAddress(Config.dbHost, Config.dbPort));
            db = mc.getDatabase(Config.database);
            Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
        } catch (Exception e) {
            AppLogger.error("From MongoPool creation ",e);
        }
    }

    public static MongoPool getInstance(){
        if(mp == null){
            mp = new MongoPool();
        }
        return mp;
    }

    public MongoCollection<Document> getCollection(String collectionName){
        MongoCollection<Document> collection;
        collection = db.getCollection(collectionName);
        return collection;
    }


}
