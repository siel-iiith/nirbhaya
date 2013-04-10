package org.nirbhaya.trending;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
public class MongoUtil {

	/**
	 * @param args
	 */
	static Properties prop = new Properties();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			loadProperties();
			MongoClient mongoClient = new MongoClient("10.2.4.238", 27017);
			DB db = mongoClient.getDB("nirbhaya");
			DBCollection coll;
			coll = db.getCollection("trends");

			Gson gson = new Gson();
			catcontentSerialize catSerialize=null;
	    	//CategoryTypes catTypeJson=null;
	    	try
			{
	    		catSerialize = gson.fromJson(new FileReader(prop.getProperty("waterLogging")), catcontentSerialize.class);
	    		System.out.println(catSerialize.catContent.get(0).imageURL);
	    		System.out.println(catSerialize.type);
	    		DBObject catTypes = (DBObject)JSON.parse(gson.toJson(catSerialize));
	    		coll.insert(catTypes);
	    		mongoClient.close();
	    		
	    		//db.trends.find({"_id":ObjectId("515ee287bdbed86c5e72ebab")})
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void loadProperties() {
		// TODO Auto-generated method stub
		try {
			// load a properties file
			prop.load(new FileInputStream("trending.properties"));
			// get the property value and print it out
		} catch (IOException ex) {
			System.out.println("No trending.properties file\n");
			ex.printStackTrace();
			System.exit(-1);
		}
	}
}
