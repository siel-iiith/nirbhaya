package org.nirbhaya.heatmap;
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

public class MongoFeed {

	public static void main(String args[]) throws IOException{
		MongoFeed m = new MongoFeed();
	}
	public MongoFeed() throws IOException {
		// TODO Auto-generated constructor stub


		MongoClient mongoClient = new MongoClient( "10.2.4.180" , 27017 );
		DB db = mongoClient.getDB( "nirbhaya" );
		Stat s1 = new Stat();
		Gson gson = new Gson();
		
		DBCollection coll = db.getCollection("heatmap");
		ArrayList<Stat> stats = new ArrayList<Stat>();
		Location delhiLocation = new Location();
		
		
		
		delhiLocation.setLocationName("Mumbai");
		delhiLocation.setLatitude(28);
		delhiLocation.setLongitude(77);
		gson = new Gson();
		s1.setLocation(delhiLocation);
		s1.setValue(100);
		stats.add(s1);
	
		
	//	stats.add(s2);
		Problems electricityProblem = new Problems();
		electricityProblem.setProblem("Crime");
		electricityProblem.setStats(stats);
		DBObject obj11 = (DBObject)JSON.parse(gson.toJson(electricityProblem)); 
		coll.insert(obj11);

		
//		Location hybLocation = new Location();

//		hybLocation.setLocationName("Mumbai");
//		hybLocation.setLatitude(15);
///		hybLocation.setLongitude(71);
		

//		Stat s2 = new Stat();
//		s2.setLocation(hybLocation);
//		s2.setValue(200);

	}
}
