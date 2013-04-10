package org.nirbhaya.heatmap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Properties;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MessageQuery {
	static Properties prop = new Properties();
	static DB db = null;
	public static void main(String args[]) throws IOException{
		MessageQuery m = new MessageQuery();
		System.out.println(m.getBothLocationProblem("delhi","murder"));
	}
	public MessageQuery(){
		try {
			prop.load(new FileInputStream("/home/romil/config.properties"));
			System.out.println(prop.getProperty("dport"));
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
		DBCollection coll = db.getCollection("heatmap1");
		Gson gson = new Gson();
		BasicDBObject query1 = new BasicDBObject("stats.location.locationName", location);
		BasicDBObject query2 = new BasicDBObject("stats.location.state", location);
		ArrayList<BasicDBObject> myList = new ArrayList<BasicDBObject>();
		myList.add(query1);
		myList.add(query2);
		BasicDBObject query = new BasicDBObject("$or", myList);

		DBCursor cursor = coll.find(query);
		String toReturn = "";

		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
				Problems prob = gson.fromJson(d.toString(),Problems.class);
				ArrayList<Stat> s = prob.stats;
				ArrayList<Stat> temp = new ArrayList<Stat>();
				for(int i=0;i<s.size();i++){
					if(s.get(i).location.locationName.equals(location)|| s.get(i).location.state.equals(location)){
						temp.add(s.get(i));
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

		problem=URLDecoder.decode(problem,"UTF-8");
		problem=problem.toLowerCase();

		DBCollection coll = db.getCollection("heatmap1");
		Gson gson = new Gson();
		String querysplit[]= problem.split("[^a-z]");
		ArrayList<BasicDBObject> myList = new ArrayList<BasicDBObject>();
		for(int i=0;i<querysplit.length;i++){
			if(querysplit[i].length()<2)
				continue;
			BasicDBObject query1 = new BasicDBObject("problem", querysplit[i]);
			myList.add(query1);
		}
		BasicDBObject query = new BasicDBObject("$or", myList);


		DBCursor cursor = coll.find(query);
		String toReturn = "";

		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
				Problems prob = gson.fromJson(d.toString(),Problems.class);
				toReturn = gson.toJson(prob);
			}
		} finally {
			cursor.close();
			
		}
		
		return "{\"result\":["+toReturn+"]}";
	}

	public String getBothLocationProblem(String location,String problem) throws IOException {
		// TODO Auto-generated constructor stub
		location=location.toLowerCase();
		problem=URLDecoder.decode(problem,"UTF-8");
		problem=problem.toLowerCase();
		

		DBCollection coll = db.getCollection("heatmap1");
		Gson gson = new Gson();
		String querysplit[]= problem.split(" ");
		ArrayList<BasicDBObject> myList = new ArrayList<BasicDBObject>();
		for(int i=0;i<querysplit.length;i++){
			if(querysplit[i].length()<2)
				continue;
			BasicDBObject query1 = new BasicDBObject("stats.location.locationName", location).append("problem",querysplit[i]);
			BasicDBObject query2 = new BasicDBObject("stats.location.state", location).append("problem",querysplit[i]);
			myList.add(query1);
			myList.add(query2);
		}
		BasicDBObject query = new BasicDBObject("$or", myList);

		//		BasicDBObject query = new BasicDBObject("stats.location.locationName", location).append("problem", problem);

		DBCursor cursor = coll.find(query);
		String toReturn = "";

		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
				Problems prob = gson.fromJson(d.toString(),Problems.class);
				ArrayList<Stat> s = prob.stats;
				ArrayList<Stat> temp = new ArrayList<Stat>();
				for(int i=0;i<s.size();i++){
					if(s.get(i).location.locationName.equals(location)|| s.get(i).location.state.equals(location)){
						temp.add(s.get(i));
						System.err.println(s.get(i).location.locationName);
					}
				}

				prob.stats = temp;
				toReturn = gson.toJson(prob);
			}
		} finally {
			cursor.close();
			
		}
		return "{\"result\":["+toReturn+"]}";
	}

	public String getAllProblem() throws IOException {
		// TODO Auto-generated constructor stub


		
		ArrayList<Problems> allProblems = new ArrayList<Problems>();
		DBCollection coll = db.getCollection("heatmap1");
		Gson gson = new Gson();

		DBCursor cursor = coll.find();
		String toReturn = "";
		try {
			while(cursor.hasNext()) {
				DBObject d = cursor.next();
				Problems prob = gson.fromJson(d.toString(),Problems.class);
				allProblems.add(prob);
			}
		} finally {
			cursor.close();
			
		}
		return "{\"result\":"+gson.toJson(allProblems)+"}";
	}
}
