package org.nirbhaya.verticalsearch;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Path("/QuerySuggestion")
public class QuerySuggestion 
{
	MongoClient mongoClient;
	DB db;
	DBCollection coll,coll2,coll3;
	Gson gson;
	HashSet<String> listOfStates=new HashSet<String>();
	String finalQuery="";
	public QuerySuggestion() throws UnknownHostException
	{
		mongoClient = new MongoClient( "10.2.4.238" , 27017 );
		db = mongoClient.getDB( "nirbhaya" );
		gson = new Gson();
		coll = db.getCollection("queryexpansionPlaces");
		coll2 = db.getCollection("queryexpansionGrievances");
		coll3 = db.getCollection("queryexpansionExtendedPlaces");
				
		DBCursor cursor = coll3.find();
		
		while(cursor.hasNext()) 
		{
			DBObject d = cursor.next();
			ExtendedPlaces place = gson.fromJson(d.toString(),ExtendedPlaces.class);
			listOfStates.add(place.getState());
		}
		
	}
	
	public static void main(String[] args) throws Exception
	{
		QuerySuggestion qs=new QuerySuggestion();
		qs.suggestQuery("india gang rape|andhra pradesh|hyderabad", "callback");
	}
	
	
		
	 @GET
	 @Produces({MediaType.APPLICATION_JSON})
	 public String suggestQuery (@QueryParam("q") String query, @QueryParam("callback") String callback) throws Exception 
	 {
		 query=query.toLowerCase();
		 this.finalQuery=query;
		 String[] refinedSearchQuery= query.split("\\|");
		 if (refinedSearchQuery.length>=2)
		 {	 
			 String part1,part2,temp;
			 if (refinedSearchQuery.length==3)
			 {	 
				 part1=refinedSearchQuery[0];
				 part2=refinedSearchQuery[2];
				 temp=refinedSearchQuery[1];
				 part1=part1.replaceAll("india", temp);
			 }
			 else
			 {	 
				 part1=refinedSearchQuery[0];
				 part2=refinedSearchQuery[1];
				  
			 }
			//String part1=refinedSearchQuery[0];
			 //String part2=refinedSearchQuery[1];
			 
			 Iterator<String> it=listOfStates.iterator();
			 while (it.hasNext())
			 {	 
				 String place=it.next();
				 if (part1.contains(place))
				 {	 
					 query=part1.replaceAll(place, part2);
					 break;
				 }
				 
		     }
			 if (part1.contains("india"))
				 query=part1.replaceAll("india", part2);
			 System.out.println("FINAL QUERY: "+query);
			 this.finalQuery=query;
		 }
		 
		 if (query.contains("india"))
		 {	 
			 Iterator<String> it=listOfStates.iterator();
			 String result = "{\"expansionResults\": [";
			 while (it.hasNext())
			 {	 
				 String state=it.next();
				 result += "{ " + "\"placeName\":\"" + state; 
				 result +="\" }," + "\n";
			 }
			 result=result.substring(0, result.length()-2);
		     //result += "]" + "\n" + "}";
			 result += "]" + "\n" + ",";
			 result += "\"finalQuery\": \""+this.finalQuery + "\"}";
			 result = callback+"("+result+");";
		     System.out.println(result);
		     return result;
		 }
		 
		 Iterator<String> it=listOfStates.iterator();
		 while (it.hasNext())
		 {	 
			 String state=it.next();
			 // System.out.println(state);
			 if (query.contains(state))
			 {	 
				 BasicDBObject mongoQuery = new BasicDBObject("state",state);
				 DBCursor cursor = coll3.find(mongoQuery);
				 
				 String result = "{\"expansionResults\": [";
				 while(cursor.hasNext()) 
				 {
					DBObject d = cursor.next();
					ExtendedPlaces place = gson.fromJson(d.toString(),ExtendedPlaces.class);
						
					result += "{ " + "\"placeName\":\"" + place.getPlace(); 
					result +="\" }," + "\n";
				 }
				 result=result.substring(0, result.length()-2);
			     //result += "]" + "\n" + "}";
				 result += "]" + "\n" + ",";
				 result += "\"finalQuery\": \""+this.finalQuery + "\"}";
				 result = callback+"("+result+");";
			     System.out.println(result);
			     return result;
			 }
		 }
		 String result = callback+"({\"finalQuery\": \""+this.finalQuery + "\"});"; 
		return result;
		 
	 }
	 
}
