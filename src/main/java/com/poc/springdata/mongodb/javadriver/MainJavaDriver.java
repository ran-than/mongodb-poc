package com.poc.springdata.mongodb.javadriver;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 * We want to insert and retrieve the following JSON
 * using MongoDB driver
 * 
 * 
 {
   "name" : "MongoDB",
   "type" : "database",
   "count" : 1,
   "info" : {
               x : 203,
               y : 102
             }
 }
 * @author Ran.ga.na.than
 *
 */

public class MainJavaDriver {
	
	private final static void saveToDBCollection(String collectionName, BasicDBObject doc) throws UnknownHostException{
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient( "localhost" , 27017 );
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw e;
		}
		
		DB database = mongoClient.getDB("driverpoc");
		DBCollection collection =  database.getCollection(collectionName);
		collection.save(doc);
	}
	
	private final static BasicDBObject createDocument(String name, String type){
		BasicDBObject doc = new BasicDBObject("name", name).
                append("type", type).
                append("count", 1).
                append("info", new BasicDBObject("x", 203).append("y", 102));
		return doc;
	}

	public static void main(String[] args) {
		System.out.println("Starting the java driver based MongoDB example...");
		
		BasicDBObject doc = createDocument("MongoDB", "database");
		try {
			saveToDBCollection("dbs", doc);
		} catch (UnknownHostException e) {
			System.err.println("Unable to save : "+ e.getLocalizedMessage());
		}
		
		System.out.println("Done, with the java driver based exmple.");
	}

}
