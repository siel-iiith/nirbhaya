package org.nirbhaya.heatmap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

public class UrlQuery {
	static Properties prop = new Properties();
	static DB db = null;
	public static void main(String args[]) throws IOException{
		
		UrlQuery q= new UrlQuery();
		//q.getPropertiesFromClasspath("config.properties");
		System.out.println(q.getBothLocationProblem("hyderabad","crime"));
	}

	private Properties getPropertiesFromClasspath(String propFileName) throws IOException
	{
		Properties props = new Properties();
		System.out.println(System.getProperty("user.dir"));
		InputStream inputStream =
				this.getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream == null)
		{
			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}

		props.load(inputStream);
		return props;
	}

	public UrlQuery(){
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
			if(allProblems.size()==0){
				DBCollection coll2 = db.getCollection("queryexpansionExtendedPlaces");
				BasicDBObject qry = new BasicDBObject("place", location);
				DBCursor curr = coll2.find(qry);
				if(curr.hasNext()) {
					DBObject d = curr.next();
					ExtendedPlaces place = gson.fromJson(d.toString(),ExtendedPlaces.class);
					//System.out.println(place.state);
					if(!place.state.equals(location))
						return getLocationProblem(place.state);
					else
						return getAllProblem();
				}
				else{					
					return getAllProblem();
				}

			}

		}catch(Exception e){
			return "{\"result\":[]}";
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
		if(toReturn.length()==0){
			return getAllProblem();
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
			if("".equals(toReturn)){
				DBCollection coll2 = db.getCollection("queryexpansionExtendedPlaces");
				BasicDBObject qry = new BasicDBObject("place", location);
				DBCursor curr = coll2.find(qry);
				if(curr.hasNext()) {
					DBObject dd = curr.next();
					ExtendedPlaces place = gson.fromJson(dd.toString(),ExtendedPlaces.class);
					System.out.println(place.state);
					if(!place.state.equals(location))
						return getBothLocationProblem(place.state,problem);
					else
						return getAllProblem();
				}
				else
				{
					return getAllProblem();

				}
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
