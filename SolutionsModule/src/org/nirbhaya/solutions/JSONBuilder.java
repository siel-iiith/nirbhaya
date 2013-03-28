package org.nirbhaya.solutions;

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
	
	public boolean isRelevantPerson (Person person, String query) {
		for (String str : query.split(" ")) {
			if (str.contains(person.getName().toLowerCase())
				|| str.contains(person.getDesignation().toLowerCase())
				|| str.contains(person.getPhone().toLowerCase())
				|| str.contains(person.getEmail().toLowerCase())
				|| str.contains(person.getDeptName().toLowerCase())
				|| str.contains(Long.toString(person.getDepartmentId()))) {
				return true;
			}
		}
		return false;
	}
	public boolean isRelevantDepartment (Department department, String query) {
		for (String str : query.split(" ")) {
			if (str.contains(department.getDeptName().toLowerCase())
				|| str.contains(department.getAddress().toLowerCase())
				|| str.contains(department.getLocation().toLowerCase())
				|| str.contains(department.getRTIInfo().toLowerCase())
				|| str.contains(department.getDepartmentType().toString().toLowerCase())
				|| str.contains(Long.toString(department.getDepartmentId()))) {
				return true;
			}
		}
		return false;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String outputJSON (@QueryParam("q") String query, @QueryParam("callback") String callback) throws IOException, JSONException 
	{
		
		Mongo mongo = new Mongo("10.2.4.180", 27017);
		DB db = mongo.getDB("nirbhaya");
		DBCollection collection = db.getCollection("Solutions");
//		System.out.println (query);
		Gson gson =new Gson();
		DBCursor cursorDoc = collection.find();
		ArrayList<Person> P1 = new ArrayList<Person>();
		ArrayList<Department> P2 = new ArrayList<Department>();
		while (cursorDoc.hasNext()) {
			String s1 = cursorDoc.next().toString();
//			System.out.println (s1);
			if (s1.contains("\"perdeptname\" :")) {
				Person person = new Person();
				person = gson.fromJson(s1,Person.class);
				if (isRelevantPerson (person, query.toLowerCase())) {
					P1.add(person);
//					System.out.println (person.getName());
				}
			}
			else {
				Department department = new Department();
				department = gson.fromJson(s1,Department.class);
				if (isRelevantDepartment (department, query.toLowerCase())) {
					P2.add(department);
					System.out.println (department.getAddress());
				}
			}
		}
		
		return callback +"({\"contactList\":"+"{\"person\":"+gson.toJson(P1)+"}"+"{\"department\":"+gson.toJson(P2)+"}})";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		outputJSON("person");
//		outputJSON("electricity");
	}

}
