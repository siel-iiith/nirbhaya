package org.nirbhaya.verticalsearch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class QueryExpansion 
{
	/*
	public static void main(String[] args) throws Exception
	{
		QueryExpansion qe = new QueryExpansion();
		System.out.println(qe.expandedQuery("gachibowli"));
	}*/
	
    public String expandedQuery (String query) throws UnknownHostException 
	{
		String result = query;
		String[] temp = query.split(" ");
		
		boolean placeFlag = false;
		boolean grievanceFlag = false;
		
		// Some MONGO Stuff
		MongoClient mongoClient = new MongoClient();
		DB db = mongoClient.getDB( "nirbhaya" );
		Gson gson = new Gson();

		DBCollection coll = db.getCollection("queryexpansionPlaces");
		DBCollection coll2 = db.getCollection("queryexpansionGrievances");
		DBCollection coll3 = db.getCollection("queryexpansionExtendedPlaces");
		
		for (String s : temp) 
		{
			BasicDBObject mongoQuery = new BasicDBObject("place", s);
			DBCursor cursor = coll.find(mongoQuery);
			
			try 
			{
				   while(cursor.hasNext()) 
				   {
					   DBObject d = cursor.next();
					   Place place = gson.fromJson(d.toString(),Place.class);
					   placeFlag=true;
				   }
				   // System.out.println(placeFlag);
			} 
			
			finally 
			{
				   cursor.close();
		    }
			
			
			mongoQuery = new BasicDBObject("grievance", s);
			cursor = coll2.find(mongoQuery);
			
			try 
			{
				   while(cursor.hasNext()) 
				   {
					   DBObject d = cursor.next();
					   Place place = gson.fromJson(d.toString(),Place.class);
					   grievanceFlag=true;
				   }
			} 
			
			finally 
			{
				   cursor.close();
		    }
			
		}
		
		if ( grievanceFlag == false ) { //Pick top trending grievance words
			result += " crime"; 
		}

		/*if ( placeFlag == false ) { //Pick India
			result += " india"; 
		}*/		
		return result;
	}
}
