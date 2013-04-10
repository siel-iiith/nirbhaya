package org.nirbhaya.heatmap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
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
	static Properties prop = new Properties();
	static DB db = null;
	public static void main(String args[]) throws IOException{
		MongoQuery m = new MongoQuery();
	}
	
	public MongoQuery(){
		try {
			prop.load(new FileInputStream("/home/romil/config.properties"));
			MongoClient mongoClient = new MongoClient( prop.getProperty("dbip") , Integer.parseInt(prop.getProperty("dport")));
			db = mongoClient.getDB( prop.getProperty("dbname") );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getLocationProblem(String location) throws IOException {
		// TODO Auto-generated constructor stub

		location=location.toLowerCase();
		ArrayList<Problems> allProblems = new ArrayList<Problems>();
			
		DBCollection coll = db.getCollection("heatmap");
		Gson gson = new Gson();
		BasicDBObject query = new BasicDBObject("stats.location.locationName", location);

		DBCursor cursor = coll.find(query);
		String toReturn = "";

		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
				
				Problems prob = gson.fromJson(d.toString(),Problems.class);
				ArrayList<Stat> s = prob.stats;
				ArrayList<Stat> temp = new ArrayList<Stat>();
				for(int i=0;i<s.size();i++){
					if(s.get(i).location.locationName.equals(location)){
						temp.add(s.get(i));
						//System.out.println(s.get(i).location.locationName);
					}
				}
				prob.stats = temp;
				allProblems.add(prob);
			}
		} finally {
			cursor.close();
		}
		return "{\"result\":"+gson.toJson(allProblems)+"}";
	}
	
	public String getProblemLocation(String problem) throws IOException {
		// TODO Auto-generated constructor stub

		problem=problem.toLowerCase();


		DBCollection coll = db.getCollection("heatmap");
		Gson gson = new Gson();
		BasicDBObject query = new BasicDBObject("problem", problem);

		DBCursor cursor = coll.find(query);
		String toReturn = "";

		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
				Problems prob = gson.fromJson(d.toString(),Problems.class);
				toReturn = gson.toJson(prob);
				//System.out.println(d.toString());
			}
		} finally {
			cursor.close();
		}
		return "{\"result\":["+toReturn+"]}";
	}
	
	public String getBothLocationProblem(String location,String problem) throws IOException {
		// TODO Auto-generated constructor stub
		location=location.toLowerCase();
		problem=problem.toLowerCase();

		DBCollection coll = db.getCollection("heatmap");
		Gson gson = new Gson();
		BasicDBObject query = new BasicDBObject("stats.location.locationName", location).append("problem", problem);

		DBCursor cursor = coll.find(query);
		String toReturn = "";

		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
				
				Problems prob = gson.fromJson(d.toString(),Problems.class);
				ArrayList<Stat> s = prob.stats;
				ArrayList<Stat> temp = new ArrayList<Stat>();
				for(int i=0;i<s.size();i++){
					if(s.get(i).location.locationName.equals(location)){
						temp.add(s.get(i));
						//System.out.println(s.get(i).location.locationName);
					}
				}
				prob.stats = temp;
				toReturn = gson.toJson(prob);
				//System.out.println(d.toString());
			}
		} finally {
			cursor.close();
		}
		return "{\"result\":["+toReturn+"]}";
	}
	
	public String getAllProblem() throws IOException {
		// TODO Auto-generated constructor stub


		ArrayList<Problems> allProblems = new ArrayList<Problems>();
		DBCollection coll = db.getCollection("heatmap");
		Gson gson = new Gson();

		DBCursor cursor = coll.find();
		String toReturn = "";
		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
				//System.out.println(d.toString());
				Problems prob = gson.fromJson(d.toString(),Problems.class);
				allProblems.add(prob);
				//System.out.println(prob.stats.get(1).location);
			}
		} finally {
			cursor.close();
		}
		return "{\"result\":"+gson.toJson(allProblems)+"}";
	}
}
