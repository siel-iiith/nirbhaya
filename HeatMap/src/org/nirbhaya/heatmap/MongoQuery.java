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

import org.nirbhaya.heatmap.*;

public class MongoQuery {

	public static void main(String args[]) throws IOException{
		MongoQuery m = new MongoQuery();
	}
	public static String getLocationProblem(String location) throws IOException {
		// TODO Auto-generated constructor stub


		MongoClient mongoClient = new MongoClient( "10.2.4.180" , 27017 );
		DB db = mongoClient.getDB( "nirbhaya" );
		ArrayList<Problems> allProblems = new ArrayList<Problems>();
			
		DBCollection coll = db.getCollection("heatmap");
		Gson gson = new Gson();
		BasicDBObject query = new BasicDBObject("stats.location.locationName", location);

		DBCursor cursor = coll.find(query);
		String toReturn = "";

		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
				
				Problems romil = gson.fromJson(d.toString(),Problems.class);
				ArrayList<Stat> s = romil.stats;
				ArrayList<Stat> temp = new ArrayList<Stat>();
				for(int i=0;i<s.size();i++){
					if(s.get(i).location.locationName.equals(location)){
						temp.add(s.get(i));
						System.out.println(s.get(i).location.locationName);
					}
				}
				romil.stats = temp;
				allProblems.add(romil);
			}
		} finally {
			cursor.close();
		}
		return "{\"result\":"+gson.toJson(allProblems)+"}";
	}
	
	public static String getProblemLocation(String problem) throws IOException {
		// TODO Auto-generated constructor stub


		MongoClient mongoClient = new MongoClient( "10.2.4.180" , 27017 );
		DB db = mongoClient.getDB( "nirbhaya" );

		DBCollection coll = db.getCollection("heatmap");
		Gson gson = new Gson();
		BasicDBObject query = new BasicDBObject("problem", problem);

		DBCursor cursor = coll.find(query);
		String toReturn = "";

		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
				Problems romil = gson.fromJson(d.toString(),Problems.class);
				toReturn = gson.toJson(romil);
				System.out.println(d.toString());
			}
		} finally {
			cursor.close();
		}
		return toReturn;
	}
	
	public static String getBothLocationProblem(String location,String problem) throws IOException {
		// TODO Auto-generated constructor stub


		MongoClient mongoClient = new MongoClient( "10.2.4.180" , 27017 );
		DB db = mongoClient.getDB( "nirbhaya" );

		DBCollection coll = db.getCollection("heatmap");
		Gson gson = new Gson();
		BasicDBObject query = new BasicDBObject("stats.location.locationName", location).append("problem", problem);

		DBCursor cursor = coll.find(query);
		String toReturn = "";

		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
				
				Problems romil = gson.fromJson(d.toString(),Problems.class);
				ArrayList<Stat> s = romil.stats;
				ArrayList<Stat> temp = new ArrayList<Stat>();
				for(int i=0;i<s.size();i++){
					if(s.get(i).location.locationName.equals(location)){
						temp.add(s.get(i));
						System.out.println(s.get(i).location.locationName);
					}
				}
				romil.stats = temp;
				toReturn = gson.toJson(romil);
				System.out.println(d.toString());
			}
		} finally {
			cursor.close();
		}
		return toReturn;
	}
	
	public static String getAllProblem() throws IOException {
		// TODO Auto-generated constructor stub


		MongoClient mongoClient = new MongoClient( "10.2.4.180" , 27017 );
		DB db = mongoClient.getDB( "nirbhaya" );
		ArrayList<Problems> allProblems = new ArrayList<Problems>();
		DBCollection coll = db.getCollection("heatmap");
		Gson gson = new Gson();

		DBCursor cursor = coll.find();
		String toReturn = "";
		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
				Problems romil = gson.fromJson(d.toString(),Problems.class);
				allProblems.add(romil);
			}
		} finally {
			cursor.close();
		}
		return "{\"result\":"+gson.toJson(allProblems)+"}";
	}
}
