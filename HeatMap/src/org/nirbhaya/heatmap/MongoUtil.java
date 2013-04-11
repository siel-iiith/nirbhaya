package org.nirbhaya.heatmap;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

public class MongoUtil {

	public static void main(String args[]) throws IOException{
		MongoUtil m = new MongoUtil();
	}
	public MongoUtil() throws IOException {
		// TODO Auto-generated constructor stub
		
		
		Properties prop = new Properties();
		InputStream ip = getClass().getResourceAsStream("config.properties");
		prop.load(ip);
		ip.close();
		MongoClient mongoClient = new MongoClient( prop.getProperty("dbip") , Integer.parseInt(prop.getProperty("dport")) );
		DB db = mongoClient.getDB( prop.getProperty("dbname") );

		DBCollection coll = db.getCollection("test");

		Location delhiLocation = new Location();
		delhiLocation.setLocationName("Delhi");
		delhiLocation.setLatitude(90);
		delhiLocation.setLongitude(90);
		
		Gson gson = new Gson();
		Problems electricityProblem = new Problems();
		electricityProblem.setProblem("Electricity");
		
		Location hybLocation = new Location();

		hybLocation.setLocationName("Hyderabad");
		hybLocation.setLatitude(90);
		hybLocation.setLongitude(90);
		//electricityProblem.setStats(tempStatMap);
		Stat s1 = new Stat();
		s1.setLocation(delhiLocation);
		s1.setValue(100);
		
		Stat s2 = new Stat();
		s2.setLocation(hybLocation);
		s2.setValue(200);
		
		ArrayList<Stat> stats = new ArrayList<Stat>();
		stats.add(s1);
		stats.add(s2);
		//electricityProblem.setStats(stats);
		DBObject obj11 = (DBObject)JSON.parse(gson.toJson(electricityProblem)); 
		//coll.insert(obj11);

		//BasicDBObject query = new BasicDBObject("stats.location.locationName", "Hyderabad").append("problem", "Water");
		BasicDBObject query = new BasicDBObject("stats.location.locationName", "Delhi");

		DBCursor cursor = coll.find(query);

		try {
		   while(cursor.hasNext()) {
			   DBObject d = cursor.next();
			   Problems prob = gson.fromJson(d.toString(),Problems.class);
			   System.out.println(d.toString());
//			   System.out.println(prob.getStats().get(1).getLocation().getLocationName());
		   }
		} finally {
		   cursor.close();
		}
		}
	}
//}