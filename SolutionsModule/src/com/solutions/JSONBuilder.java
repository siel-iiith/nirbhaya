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
	
	public int isRelevantPerson (Person person, String query) {
		int score = 0;
		String prev = "";
		for (String str : query.split(" ")) {
			if ((str.contains(person.getName().toLowerCase()) || person.getName().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(person.getName().toLowerCase()) || person.getName().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if ((str.contains(person.getDesignation().toLowerCase()) || person.getDesignation().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(person.getDesignation().toLowerCase()) || person.getDesignation().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if ((str.contains(person.getDeptName().toLowerCase()) || person.getDeptName().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(person.getDeptName().toLowerCase()) || person.getDeptName().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if ((str.contains(person.getEmail().toLowerCase()) || person.getEmail().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(person.getEmail().toLowerCase()) || person.getEmail().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			prev = str;
		}
		return score;
	}
	public int isRelevantDepartment (Department department, String query) {
		int score = 0;
		String prev = "";
		for (String str : query.split(" ")) {
			if ((str.contains(department.getDeptName().toLowerCase()) || department.getDeptName().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(department.getDeptName().toLowerCase()) || department.getDeptName().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if ((str.contains(department.getAddress().toLowerCase()) || department.getAddress().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(department.getAddress().toLowerCase()) || department.getAddress().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if ((str.contains(department.getLocation().toLowerCase()) || department.getLocation().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(department.getLocation().toLowerCase()) || department.getLocation().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if ((str.contains(department.getDepartmentType().toString().toLowerCase()) || department.getDepartmentType().toString().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(department.getDepartmentType().toString().toLowerCase()) || department.getDepartmentType().toString().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			prev = str;
		}
		return score;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String outputJSON (@QueryParam("q") String query, @QueryParam("callback") String callback) throws IOException, JSONException 
	{
		
		Mongo mongo = new Mongo("10.2.4.238", 27017);
		DB db = mongo.getDB("nirbhaya");
		DBCollection collection = db.getCollection("Solutions");
//		System.out.println (query);
		Gson gson =new Gson();
		DBCursor cursorDoc = collection.find();
		ArrayList<ArrayList<Person>> P1 = new ArrayList<ArrayList<Person>>();
		P1.add(new ArrayList<Person>());
		P1.add(new ArrayList<Person>());
		P1.add(new ArrayList<Person>());
		P1.add(new ArrayList<Person>());
		ArrayList<ArrayList<Department>> P2 = new ArrayList<ArrayList<Department>>();
		P2.add(new ArrayList<Department>());
		P2.add(new ArrayList<Department>());
		P2.add(new ArrayList<Department>());
		P2.add(new ArrayList<Department>());
		ArrayList<Person> personList = new ArrayList<Person>();
		ArrayList<Department> departmentList = new ArrayList<Department>();
		while (cursorDoc.hasNext()) {
			int score = 0;
			String s1 = cursorDoc.next().toString();
//			System.out.println (s1);
			if (s1.contains("\"perdeptname\" :")) {
				Person person = new Person();
				person = gson.fromJson(s1,Person.class);
				if ((score = isRelevantPerson(person, query.toLowerCase())) > 0) {
					P1.get(score-1).add(person);
//					System.out.println (person.getName());
				}
			}
			else {
				Department department = new Department();
				department = gson.fromJson(s1,Department.class);
				if ((score = isRelevantDepartment(department, query.toLowerCase())) > 0) {
					P2.get(score-1).add(department);
//					System.out.println (department.getAddress());
				}
			}
		}
		for (int i=3; i >= 0; i--) {
			if (P1.get(i).size() > 0) {
				for (Person person : P1.get(i)) {
					personList.add(person);
				}
			}
			if (P2.get(i).size() > 0) {
				for (Department department : P2.get(i)) {
					departmentList.add(department);
				}
			}
		}
		
		return callback +"({\"contactList\":"+"{\"person\":"+gson.toJson(personList)+",\"department\":"+gson.toJson(departmentList)+"}})";
//		return callback +"({\"contactList\":"+gson.toJson(P1)+"})";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		outputJSON("person");
//		outputJSON("electricity");
	}

}
