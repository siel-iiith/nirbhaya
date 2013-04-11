package com.solutions;

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
import com.mongodb.MongoClient;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.solutions.SynonymDB.Synonym;

@Path("/Solution")
public class JSONBuilder {
	
	public int isRelevantPerson (Person person, String query, HashMap<String,String> mapping) {
		int score = 0;
		String prev = "";
		englishStemmer stemmer = new org.tartarus.snowball.ext.englishStemmer();
		for (String str : query.split(" ")) {
			stemmer.setCurrent(str);
			stemmer.stem();
			String stemmed = stemmer.getCurrent();
			if (!person.getName().equals("") && (str.contains(person.getName().toLowerCase()) || person.getName().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(person.getName().toLowerCase()) || person.getName().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if (!person.getDesignation().equals("") && (str.contains(person.getDesignation().toLowerCase()) || person.getDesignation().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(person.getDesignation().toLowerCase()) || person.getDesignation().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if (!person.getLocation().equals("") && (str.contains(person.getLocation().toLowerCase()) || person.getLocation().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(person.getLocation().toLowerCase()) || person.getLocation().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if (!person.getDeptName().equals("") && mapping.containsKey(stemmed)) {
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
			if (!department.getAddress().equals("") && (str.contains(department.getAddress().toLowerCase()) || department.getAddress().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(department.getAddress().toLowerCase()) || department.getAddress().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if (!department.getLocation().equals("") && (str.contains(department.getLocation().toLowerCase()) || department.getLocation().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(department.getLocation().toLowerCase()) || department.getLocation().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if (!department.getDepartmentType().toString().equals("") && (str.contains(department.getDepartmentType().toString().toLowerCase()) || department.getDepartmentType().toString().toLowerCase().contains(str)) && (prev.equals("") || !(prev.contains(department.getDepartmentType().toString().toLowerCase()) || department.getDepartmentType().toString().toLowerCase().contains(prev)))) {
				score = score + 1;
			}
			else if (!department.getDeptName().equals("") && mapping.containsKey(stemmed)) {
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
	{
		MongoClient mongo = new MongoClient();
//		Mongo mongo = new Mongo("10.2.4.238", 27017);
		DB db = mongo.getDB("nirbhaya");
		Gson gson =new Gson();
		englishStemmer stemmer = new org.tartarus.snowball.ext.englishStemmer();
		HashMap<String,String> mapping = new HashMap<String,String>();
		DBCollection syn = db.getCollection("Synonyms");
		DBCursor synonyms = syn.find();
		while (synonyms.hasNext()) {
			SynonymDB.Synonym s = new SynonymDB.Synonym();
			s = gson.fromJson(synonyms.next().toString(), Synonym.class);
			String dept = s.synonyms;
			stemmer.setCurrent(s.word);
			stemmer.stem();
			String stemmed = stemmer.getCurrent();
			if (!mapping.containsKey(stemmed)) {
				mapping.put(stemmed.toLowerCase(), dept.toLowerCase());
			}
		}
		DBCollection collection = db.getCollection("Solutions");
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
		while (cursorDoc.hasNext() && !query.equals("")) {
			int score = 0;
			String s1 = cursorDoc.next().toString();
			if (s1.equals("")) {
				continue;
			}
			if (s1.contains("\"perdeptname\" :")) {
				Person person = new Person();
				person = gson.fromJson(s1,Person.class);
				if ((score = isRelevantPerson(person, query.toLowerCase(), mapping)) > 0) {
					P1.get(score-1).add(person);
				}
			}
			else {
				Department department = new Department();
				department = gson.fromJson(s1,Department.class);
				if ((score = isRelevantDepartment(department, query.toLowerCase(), mapping)) > 0) {
					P2.get(score-1).add(department);
				}
			}
		}
		for (int i=3; i >= 0; i--) {
//			if (!(P1.get(3).size() == 0 || P1.get(2).size() == 0 || P1.get(1).size() == 0) && !(P2.get(3).size() == 0 || P2.get(2).size() == 0 || P2.get(1).size() == 0) && i == 0) {
//				break;
//			}
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
	}

}
