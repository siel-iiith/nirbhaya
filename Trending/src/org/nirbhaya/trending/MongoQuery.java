package org.nirbhaya.trending;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;



public class MongoQuery {

		
	public static String getCatTypes(String key,String value) throws IOException {
		// TODO Auto-generated constructor stub
		MongoClient mongoClient = new MongoClient( "10.2.4.238" , 27017 );
		DB db = mongoClient.getDB( "nirbhaya" );

		DBCollection coll = db.getCollection("trends");
		Gson gson = new Gson();
		//BasicDBObject query = new BasicDBObject("ObjectId(\"515ee290bdbee4fe1bd6c6d8\")","");
		
		BasicDBObject query = new BasicDBObject(key,value);
		DBCursor cursor = coll.find(query);
		String toReturn = "";
		catcontentSerialize catTypes=new catcontentSerialize();
		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
			
				 catTypes = gson.fromJson(d.toString(),catcontentSerialize.class);
				//return gson.toJson(catTypes);
				
			}
		} finally {
			cursor.close();
			mongoClient.close();
		}
		System.out.println(gson.toJson(catTypes));
		return gson.toJson(catTypes);
	}
}
