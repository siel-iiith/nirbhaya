package com.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.UnknownHostException;

import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import com.solutions.Department.department;


import org.codehaus.jettison.json.JSONException;
import com.google.gson.Gson;

/*import com.saplo.api.client.SaploClient;
import com.saplo.api.client.entity.SaploCollection;
import com.saplo.api.client.entity.SaploCollection.Language;
import com.saplo.api.client.entity.SaploTag;
import com.saplo.api.client.entity.SaploText;
import com.saplo.api.client.manager.SaploCollectionManager;
import com.saplo.api.client.manager.SaploTextManager;*/

public class TestingWithSaploAPI {
	public static void main(String args[]) throws Exception
	{
		try{
			
			final String dir = System.getProperty("user.dir");
			BufferedReader contact = new BufferedReader(new FileReader(new File(dir+"/SolutionsModule/Resource/contacts.txt")));
			BufferedReader Deptcontact = new BufferedReader(new FileReader(new File(dir+"/SolutionsModule/Resource/departments.txt")));
			
			Mongo mongo = new Mongo("10.2.4.180", 27017);
			DB db = mongo.getDB("nirbhaya");
			DBCollection collection = db.getCollection("Solutions");
			
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
			    //collection.remove(dbObject);
			
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
				depts.setRTIInfo(sp1[4]);
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
				
			   // collection.remove(dbObject1);
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
		
		/*SaploClient client = new SaploClient("a69ef0eb267e3578963a465b2b871208", "e88b050934321f88acb257fda2e892e2");
		// Your access_token is stored in the client.
		// If you want to retrieve it, you can
		String accessToken = client.getAccessToken();
		System.out.println(accessToken);
		
		// First you need to create a manager to work with collections
		SaploCollectionManager collectionMgr = new SaploCollectionManager(client);
		
		// Then create a collection object
		SaploCollection collection = new SaploCollection("My Collection Name", Language.en);
		 
		// Save the created collection in the API using the manager
		collectionMgr.create(collection);
		
		// First create a manager to work with text
		SaploTextManager textMgr = new SaploTextManager(client);
		 
		// Then create a text object in your collection
		SaploText text = new SaploText(collection, "Some cool text about Apple - the company, goes here");
		text.setHeadline("Some cool headline");
		text.setAuthors("Me");
		 
		// Then save your text using the manager
		textMgr.create(text);
		
		// Get a list of tags that exist in your text
		java.util.List<SaploTag> tags = textMgr.tags(text);
		
		// Print them out
		for(SaploTag tag: tags)
		    System.out.println("Category: " + tag.getCategory()
		        + " TagWord: " + tag.getTagWord());*/
	}
}