/*
 * Joe Hernandez
 * Write a Java program that accesses the database, creates a document, updates a document and deletes a document.
 *
 * */

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

public class JavaTestClient {


    public static void main(String[] args) {
        //Atlas connection
//        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
//        MongoClient mongoClient = new MongoClient(connectionString);


        try {

            MongoClient mongoClient = new MongoClient("localhost", 27017);
            //Connect to database.
            MongoDatabase database = mongoClient.getDatabase("PhoneInventory");
            MongoCollection<Document> phoneCollection = database.getCollection("Cellphone");

            //Create object.
            Document phone = new Document("make", "LG")
                    .append("model", "G6")
                    .append("color", "Gold")
                    .append("price", 170);

            //Insert object into collection.
            phoneCollection.insertOne(phone);


            // Delete Car
            phoneCollection.deleteOne(new Document("model", "iPhone X"));


            //Update price
            Bson filter = new Document("model", "S6");
            Bson newValue = new Document("price", 80);
            Bson updateOperationDocument = new Document("$set", newValue);
            phoneCollection.updateOne(filter, updateOperationDocument);


            //Print all.
            for (Document cur : phoneCollection.find()) {
                System.out.println(cur.toJson());
            }


        } catch (Exception e) {
            System.out.println("Connection error, try again.");
        }


    }
}