package org.nirbhaya.trending;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

import java.util.Arrays;
import java.util.Set;
public class MongoUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
		MongoClient mongoClient = new MongoClient( "10.2.4.238" , 27017 );
		DB db = mongoClient.getDB( "nirbhaya" );
		Set<String> colls = db.getCollectionNames();

		for (String s : colls) {
		    System.out.println(s);
		}
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
