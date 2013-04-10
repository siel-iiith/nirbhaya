package org.nirbhaya.heatmap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class UrlFeed {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	static Properties prop = new Properties();
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		UrlFeed.MongoFeed();
	}
	public static void MongoFeed() throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
		prop.load(new FileInputStream("/home/romil/config.properties"));

		MongoClient mongoClient = new MongoClient( prop.getProperty("dbip") , Integer.parseInt(prop.getProperty("dport")) );
		DB db = mongoClient.getDB( prop.getProperty("dbname") );
		Gson gson = new Gson();

		DBCollection coll = db.getCollection(prop.getProperty("dbcollection"));
		ArrayList<Stat> stats = new ArrayList<Stat>();


		BufferedReader reader = new BufferedReader(new FileReader("murder.tsv"));
		String line = null;
		System.out.println("test");
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			Stat s1 = new Stat();
			String[] sr=line.split("\t");
			System.out.println(sr[0]);
			System.out.println(sr[1]);
			System.out.println(sr[2]);
			Location location = new Location();
			location.setState(sr[0].toLowerCase());
			location.setLocationName(sr[1].toLowerCase());
			String  newURL = "http://maps.googleapis.com/maps/api/geocode/json?address="+URLEncoder.encode(sr[1].toLowerCase()+","+sr[0].toLowerCase())+"&sensor=false";
			System.out.println(newURL);
			//System.exit(0);
			System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
			System.setProperty("http.proxyPort", "8080");
			URL oracle = new URL(newURL);
			Thread.sleep(1000);
			String googleout="";
			BufferedReader in = new BufferedReader(
					new InputStreamReader(oracle.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				googleout+=inputLine;
			in.close();
			JsonParser parser = new JsonParser();
			System.out.println(googleout);
			//parser.parse(inputLine.toString()).getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location");
			System.err.println(parser.parse(googleout.toString()).getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lat").getAsDouble());
			location.setLatitude(parser.parse(googleout.toString()).getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lat").getAsDouble());
			location.setLongitude(parser.parse(googleout.toString()).getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lng").getAsDouble());
			gson = new Gson();
			s1.setLocation(location);
			s1.setValue(100);
			s1.setSnippet(sr[2].toLowerCase());
			s1.setUrl(sr[3].toLowerCase());
			stats.add(s1);
		}

		Problems problem = new Problems();
		String prob="assault";
		problem.setProblem(prob.toLowerCase());
		problem.setStats(stats);
		DBObject obj11 = (DBObject)JSON.parse(gson.toJson(problem)); 
		coll.insert(obj11);
	}
}


