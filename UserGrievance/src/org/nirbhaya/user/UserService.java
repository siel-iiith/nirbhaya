package org.nirbhaya.user;

import java.net.UnknownHostException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

@Path("/grievance")
public class UserService {
	private static MongoClient mongoClient;
	private static DB db;
	private static DBCollection grievanceCollection;

	static{
		try {
			mongoClient = new MongoClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		db = mongoClient.getDB("nirbhaya");
		grievanceCollection = db.getCollection("grievanceCollection");
	}
	
	@Path("add")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String addGrievance(@QueryParam("name") String name, @QueryParam("type") String type,
			@QueryParam("location") String location, @QueryParam("comments") String comments, @QueryParam("callback") String callback) {
		Gson gson = new Gson();
		System.out.println(name);
		Grievance g = new Grievance(name,type,location,comments);
		System.out.println(gson.toJson(g));
		DBObject obj = (DBObject)JSON.parse(gson.toJson(g));
		grievanceCollection.save(obj);
		return "success";
		//return grievanceCollection.insert((DBObject)JSON.parse(new Gson().toJson(new Grievance(name,type,location,comments)))).toString();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getGrievance(@QueryParam("name") String name, @QueryParam("callback") String callback) {
		BasicDBObject query = new BasicDBObject("name", name);
        DBCursor cur = grievanceCollection.find(query);
        JsonArray result = new JsonArray();
        while(cur.hasNext()){
        	String ret = cur.next().toString();
        	result.add(new JsonParser().parse(ret));
        }
        cur.close();
		return result.toString();
	}

}