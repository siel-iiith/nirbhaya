package com.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

@Path("/Solution")
public class JSONBuilder {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String outputJSON (@QueryParam("q") String query, @QueryParam("callback") String callback) throws IOException, JSONException 
	{
		
		Mongo mongo = new Mongo("10.2.4.180", 27017);
		DB db = mongo.getDB("nirbhaya");
		DBCollection collection = db.getCollection("Solutions");
		
		Gson gson =new Gson();
		DBCursor cursorDoc = collection.find();
		ArrayList<Person> P1 = new ArrayList<Person>();
		while (cursorDoc.hasNext()) {
			P1.add(gson.fromJson(cursorDoc.next().toString(),Person.class));
		}
		
		return callback +"({\"contactList\":"+gson.toJson(P1)+"})";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		outputJSON("person");
//		outputJSON("electricity");
	}

}
