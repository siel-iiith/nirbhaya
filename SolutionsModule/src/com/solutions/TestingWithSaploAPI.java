package com.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import com.solutions.Department.department;

import com.google.gson.Gson;

public class TestingWithSaploAPI {
	public static void main(String args[]) throws Exception
	{
		try{
			
			final String dir = System.getProperty("user.dir");
			BufferedReader contact = new BufferedReader(new FileReader(new File(dir+"/Resource/contacts.txt")));
			BufferedReader Deptcontact = new BufferedReader(new FileReader(new File(dir+"/Resource/departments.txt")));
			
			Mongo mongo = new Mongo("10.2.4.238", 27017);
			DB db = mongo.getDB("nirbhaya");
			DBCollection collection = db.getCollection("Solutions");
			collection.remove(new BasicDBObject());
			
			String str = contact.readLine();
			while (str!= null)
			{
				Person contactList = new Person();
				String[] sp = str.split("\t");
				contactList.setName(sp[0]);
				contactList.setPerDeptName(sp[1]);
				contactList.setDesignation(sp[2]);
				contactList.setPhone(sp[3]);
				contactList.setEmail(sp[4]);
				contactList.setDepartmentId(Long.parseLong(sp[5]));
			
				Gson gson = new Gson();
				String jsonobj = null;
				jsonobj = gson.toJson(contactList);
			
				DBObject dbObject = (DBObject)JSON.parse(jsonobj);
				collection.insert(dbObject);
			
				str = contact.readLine();
			}
			contact.close();
			
			String str1 = Deptcontact.readLine();
			while (str1!= null)
			{
				Department depts = new Department();
				String[] sp1 = str1.split("\t");
				depts.setDeptName(sp1[0]);
			    depts.setDepartmentId(Long.parseLong(sp1[1]));
				depts.setAddress(sp1[2]);
				depts.setLocation(sp1[3]);
				depts.setDeptURL(sp1[4]);
				if (sp1[5].equals("CENTRAL"))
					depts.setDepartmentType(department.CENTRAL);
				if (sp1[5].equals("STATE"))
					depts.setDepartmentType(department.STATE);
				if (sp1[5].equals("NGO"))
					depts.setDepartmentType(department.NGO);
				
				Gson gson = new Gson();
				String jsonobj1 = null;
				jsonobj1 = gson.toJson(depts);
				
				DBObject dbObject1 = (DBObject)JSON.parse(jsonobj1);
				collection.insert(dbObject1);
				
				str1 = Deptcontact.readLine();
			}
			Deptcontact.close();		
		
			DBCursor cursorDoc = collection.find();
			while (cursorDoc.hasNext()) {
				System.out.println(cursorDoc.next());
			}
 
			System.out.println("Done");
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
}