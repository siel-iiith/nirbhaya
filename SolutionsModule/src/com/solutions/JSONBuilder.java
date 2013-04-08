package com.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.tartarus.snowball.ext.englishStemmer;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

@Path("/Solution")
public class JSONBuilder {
	
/*	HashMap<String,String> mapping = new HashMap<String,String>();
	JSONBuilder() throws IOException {
		String str;
		final String dir = System.getProperty("user.dir");
		BufferedReader synset = new BufferedReader(new FileReader(new File(dir+"/Resource/synonyms-nirbhaya.txt")));
		while ((str = synset.readLine()) != null) {
			String dept = str.split(":")[0];
			for (String s : str.split(":")[1].split(", ")) {
				if (!mapping.containsKey(s)) {
					mapping.put(s, dept);
				}
			}
		}
		synset.close();
	}*/
	
	public int isRelevantPerson (Person person, String query, HashMap<String,String> mapping) {
		int score = 0;
		String prev = "";
		englishStemmer stemmer = new org.tartarus.snowball.ext.englishStemmer();
		for (String str : query.split(" ")) {
			stemmer.setCurrent(str);
			stemmer.stem();
			String stemmed = stemmer.getCurrent();
			if ((str.contains(person.getName().toLowerCase()) || person.getName().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(person.getName().toLowerCase()) || person.getName().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if ((str.contains(person.getDesignation().toLowerCase()) || person.getDesignation().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(person.getDesignation().toLowerCase()) || person.getDesignation().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if ((str.contains(person.getEmail().toLowerCase()) || person.getEmail().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(person.getEmail().toLowerCase()) || person.getEmail().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if (mapping.containsKey(stemmed)) {
				for (String s : mapping.get(stemmed).split(";")) {
					if (s.contains(person.getDeptName().toLowerCase()) || person.getDeptName().toLowerCase().contains(s)) {
						score = score + 1;
						break;
					}
				}
			}
			prev = str;
		}
		return score;
	}
	public int isRelevantDepartment (Department department, String query, HashMap<String,String> mapping) {
		int score = 0;
		String prev = "";
		englishStemmer stemmer = new org.tartarus.snowball.ext.englishStemmer();
		for (String str : query.split(" ")) {
			stemmer.setCurrent(str);
			stemmer.stem();
			String stemmed = stemmer.getCurrent();
			if ((str.contains(department.getAddress().toLowerCase()) || department.getAddress().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(department.getAddress().toLowerCase()) || department.getAddress().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if ((str.contains(department.getLocation().toLowerCase()) || department.getLocation().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(department.getLocation().toLowerCase()) || department.getLocation().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if ((str.contains(department.getDepartmentType().toString().toLowerCase()) || department.getDepartmentType().toString().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(department.getDepartmentType().toString().toLowerCase()) || department.getDepartmentType().toString().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if (mapping.containsKey(stemmed)) {
				for (String s : mapping.get(stemmed).split(";")) {
					if (s.contains(department.getDeptName().toLowerCase()) || department.getDeptName().toLowerCase().contains(s)) {
						score = score + 1;
						break;
					}
				}
			}
			prev = str;
		}
		return score;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String outputJSON (@QueryParam("q") String query, @QueryParam("callback") String callback) throws IOException 
//	public String outputJSON (String query) throws IOException, JSONException 
	{
//		JSONBuilder jsonBuilder = new JSONBuilder();
		englishStemmer stemmer = new org.tartarus.snowball.ext.englishStemmer();
		HashMap<String,String> mapping = new HashMap<String,String>();
		String str;
//		System.out.println (System.getProperty("user.dir"));
		final String dir = System.getProperty("user.dir");
		BufferedReader synset = new BufferedReader(new FileReader(new File(dir+"/Resource/synonyms-nirbhaya.txt")));
		while ((str = synset.readLine()) != null) {
			String dept = str.split(":")[0];
			for (String s : str.split(":")[1].split(", ")) {
				stemmer.setCurrent(s);
				stemmer.stem();
				String stemmed = stemmer.getCurrent();
				if (!mapping.containsKey(stemmed)) {
//					System.out.println (stemmer.getCurrent());
					mapping.put(stemmed.toLowerCase(), dept.toLowerCase());
				}
			}
		}
		synset.close();
		Mongo mongo = new Mongo("10.2.4.238", 27017);
		DB db = mongo.getDB("nirbhaya");
		DBCollection collection = db.getCollection("Solutions");
//		System.out.println (mapping.get("electr"));
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
				if ((score = isRelevantPerson(person, query.toLowerCase(), mapping)) > 0) {
					P1.get(score-1).add(person);
//					System.out.println (person.getName());
				}
			}
			else {
				Department department = new Department();
				department = gson.fromJson(s1,Department.class);
				if ((score = isRelevantDepartment(department, query.toLowerCase(), mapping)) > 0) {
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
//		return "({\"contactList\":"+"{\"person\":"+gson.toJson(personList)+",\"department\":"+gson.toJson(departmentList)+"}})";
	}
	
//	public static void main(String[] args) throws IOException, JSONException {
		// TODO Auto-generated method stub
//		outputJSON("person");
//		System.out.println (outputJSON("electricity",""));
//	}

}
