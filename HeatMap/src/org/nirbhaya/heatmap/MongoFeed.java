package org.nirbhaya.heatmap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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


		MongoClient mongoClient = new MongoClient( "10.2.4.238" , 27017 );
		DB db = mongoClient.getDB( "nirbhaya" );
		Gson gson = new Gson();

		DBCollection coll = db.getCollection("heatmap");
		ArrayList<Stat> stats = new ArrayList<Stat>();


		BufferedReader reader = new BufferedReader(new FileReader("murder.tsv"));
		String line = null;
		while ((line = reader.readLine()) != null) {
			Stat s1 = new Stat();
			String[] sr=line.split("\t");
			System.out.println(sr[0]);
			System.out.println(sr[1]);
			System.out.println(sr[2]);
			Location delhiLocation = new Location();
			delhiLocation.setLocationName(sr[0].toLowerCase());
			delhiLocation.setLatitude(Double.parseDouble(sr[1]));
			delhiLocation.setLongitude(Double.parseDouble(sr[2]));
			gson = new Gson();
			s1.setLocation(delhiLocation);
			s1.setValue(100);
			stats.add(s1);
		}

		Problems problem = new Problems();
		String prob="Murder";
		problem.setProblem(prob.toLowerCase());
		problem.setStats(stats);
		DBObject obj11 = (DBObject)JSON.parse(gson.toJson(problem)); 
		coll.insert(obj11);

	}
}
