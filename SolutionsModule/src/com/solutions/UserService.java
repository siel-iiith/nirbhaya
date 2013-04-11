package com.solutions;

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
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

@Path("/addsolution")
public class UserService {
	private static MongoClient mongo;
//	private static Mongo mongo;
	private static DB db;
	private static DBCollection solutionCollection;

	static{
		try {
//			mongo = new Mongo("10.2.4.238", 27017);
			mongo = new MongoClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		db = mongo.getDB("nirbhaya");
		solutionCollection = db.getCollection("Solutions");
	}
	
	@Path("add")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String addSolution(@QueryParam("name") String name, @QueryParam("designation") String designation,
			@QueryParam("location") String location, @QueryParam("emailid") String emailid, @QueryParam("phone") String phone, @QueryParam("perdeptname") String perdeptname, @QueryParam("callback") String callback) {
		if (name != null && designation != null && emailid != null && location != null && perdeptname != null && phone != null
			&& !name.equals("")  && !designation.equals("") && !emailid.equals("") && !location.equals("") && !perdeptname.equals("") && !phone.equals("")) {
			Gson gson = new Gson();
			Person person = new Person();
			person.setName(name);
			person.setDesignation(designation);
			person.setEmail(emailid);
			person.setLocation(location);
			person.setPerDeptName(perdeptname);
			person.setPhone(phone);
//			System.out.println(gson.toJson(person));
			DBObject obj = (DBObject)JSON.parse(gson.toJson(person));
			solutionCollection.save(obj);
		}
		return callback+"({result : \"Success\"})";
		//return grievanceCollection.insert((DBObject)JSON.parse(new Gson().toJson(new Grievance(name,type,location,comments)))).toString();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getSolution(@QueryParam("name") String name, @QueryParam("callback") String callback) {
		BasicDBObject query = new BasicDBObject("name", name);
        DBCursor cur = solutionCollection.find(query);
        JsonArray result = new JsonArray();
        while(cur.hasNext()){
        	String ret = cur.next().toString();
        	result.add(new JsonParser().parse(ret));
        }
        cur.close();
		return result.toString();
	}

}
