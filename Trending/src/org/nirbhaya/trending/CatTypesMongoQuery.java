package org.nirbhaya.trending;

import java.io.IOException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class CatTypesMongoQuery {

	/**
	 * @param args
	 */
	public static String getCatTypes(String key,String value) throws IOException {
		// TODO Auto-generated constructor stub
		MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );
		DB db = mongoClient.getDB( "nirbhaya" );

		DBCollection coll = db.getCollection("trends");
		Gson gson = new Gson();
		BasicDBObject query = new BasicDBObject(key,value);
		DBCursor cursor = coll.find(query);
		String toReturn = "";
		CategoryTypes catTypes=new CategoryTypes();
		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
			
				 catTypes = gson.fromJson(d.toString(),CategoryTypes.class);
				
				
			}
		} finally {
			cursor.close();
			mongoClient.close();
		}
		System.out.println(gson.toJson(catTypes));
		return gson.toJson(catTypes);
	}
}
