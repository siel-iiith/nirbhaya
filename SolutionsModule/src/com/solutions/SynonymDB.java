package com.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

//This class will always be used in an offline setting.
public class SynonymDB {

	public static class Synonym {
		String word;
		String synonyms;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		final String dir = System.getProperty("user.dir");
		BufferedReader file = new BufferedReader(new FileReader(new File(dir+"/Resource/synonyms-nirbhaya.txt")));
		
		MongoClient mongo = new MongoClient();
//		Mongo mongo = new Mongo("10.2.4.238", 27017);
		DB db = mongo.getDB("nirbhaya");
		DBCollection collection = db.getCollection("Synonyms");
		collection.remove(new BasicDBObject());
		String str;
		while ((str = file.readLine()) != null) {
			String dept = str.split(":")[0];
			for (String s : str.split(":")[1].split(", ")) {
				Synonym relation = new Synonym();
				relation.word = s;
				relation.synonyms = dept;
				Gson gson = new Gson();
				String jsonobj = null;
				jsonobj = gson.toJson(relation);			
				DBObject dbObject = (DBObject)JSON.parse(jsonobj);
				collection.insert(dbObject);
			}
		}
		file.close();
		DBCursor cursorDoc = collection.find();
		while (cursorDoc.hasNext()) {
			System.out.println(cursorDoc.next());
		}
		System.out.println("Done");
	}

}
