package com.kgc.test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Test {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
       /* MongoClientURI c = new MongoClientURI("mongodb://root:root@localhost:27017");
        MongoClient mongoClient = new MongoClient(c);*/
        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> collection = database.getCollection("startup_log");
        Document myDoc = collection.find().first();
        String json = myDoc.toJson();

        System.out.println(json);
    }
}
