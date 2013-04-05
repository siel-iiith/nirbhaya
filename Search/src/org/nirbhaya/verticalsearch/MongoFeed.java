package org.nirbhaya.verticalsearch;
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

// This class will always be used in an offline setting.
public class MongoFeed {

	public static void main(String args[]) throws IOException{
		MongoFeed m = new MongoFeed();
	}
	public MongoFeed() throws IOException {
		// TODO Auto-generated constructor stub


		MongoClient mongoClient = new MongoClient( "10.2.4.238" , 27017 );
		DB db = mongoClient.getDB( "nirbhaya" );
		Gson gson = new Gson();

		//DBCollection coll = db.getCollection("queryexpansionPlaces");
		// DBCollection coll = db.getCollection("queryexpansionGrievances");		
		DBCollection coll = db.getCollection("queryexpansionExtendedPlaces");
		
		BufferedReader reader = new BufferedReader(new FileReader("/home/nikhil/Downloads/places"));
		String line = null;
		DBObject obj=null;
		while ((line = reader.readLine()) != null) 
		{
			// Place place = new Place(line.trim().toLowerCase());
			// Grievance grievance =new Grievance(line.trim().toLowerCase());
			
			String[] extendedPlaceInfo=line.split("\t");
			ExtendedPlaces extendedplaces = null;
			
			if(extendedPlaceInfo.length==4)
			{
				extendedplaces =new ExtendedPlaces(extendedPlaceInfo[0].toLowerCase(), extendedPlaceInfo[1].toLowerCase(), extendedPlaceInfo[2].toLowerCase(), extendedPlaceInfo[3].toLowerCase());
			}
			// if length == 3 population is not present
			else if(extendedPlaceInfo.length==3)
			{	
				extendedplaces =new ExtendedPlaces(extendedPlaceInfo[0].toLowerCase(), extendedPlaceInfo[1].toLowerCase(), extendedPlaceInfo[2].toLowerCase(), "NULL");
			}
			// if length == 2 areatype, population both are not present
			else if(extendedPlaceInfo.length==2)
			{
				extendedplaces =new ExtendedPlaces(extendedPlaceInfo[0].toLowerCase(), extendedPlaceInfo[1].toLowerCase(), "NULL", "NULL");
			}
			
			
			//obj = (DBObject)JSON.parse(gson.toJson(place)); 
			// obj = (DBObject)JSON.parse(gson.toJson(grievance));
			obj = (DBObject)JSON.parse(gson.toJson(extendedplaces));
			coll.insert(obj);
			
		}


		// TO BE USED ONLY FOR EMERGENCY PURPOSE
		
//		BasicDBObject query = new BasicDBObject("place", "hyderabad");
//		DBCursor cursor = coll.find(query);
//
//		boolean placeFlag=false;
//		try {
//		   while(cursor.hasNext()) 
//		   {
//			   DBObject d = cursor.next();
//			   Place place = gson.fromJson(d.toString(),Place.class);
//			   placeFlag=true;
//		   }
//		   System.out.println(placeFlag);
//		} finally {
//		   cursor.close();}
	}
}


/* AREA TYPE CONVENTIONS/ABBREVIATIONS

C.B - Cantonment Board / Cantonment

C.M.C – City Municipal Council

C.T – Census Town

E.O – Estate Office

G.P - Gram Panchayat

I.N.A – Industrial Notified Area

I.T.S - Industrial Township

M – Municipality

M.B – Municipal Board

M.C – Municipal Committee

M.Cl – Municipal Council

M.Corp. – Municipal Corporation / Corporation

N.A – Notified Area

N.A.C – Notified Area Committee / Notified Area Council

N.P – Nagar Panchayat

N.T – Notified Town

N.T.A – Notified Town Area

S.T.C - Small Town Committee

T.C – Town Committee / Town Area Committee

T.M.C – Town Municipal Council

T.P – Town Panchayat

T.S.- Township

U.A – Urban Agglomeration
*/
