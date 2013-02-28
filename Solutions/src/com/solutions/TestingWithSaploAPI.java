package com.solutions;
import com.saplo.api.client.SaploClient;
import com.saplo.api.client.entity.SaploCollection;
import com.saplo.api.client.entity.SaploCollection.Language;
import com.saplo.api.client.entity.SaploTag;
import com.saplo.api.client.entity.SaploText;
import com.saplo.api.client.manager.SaploCollectionManager;
import com.saplo.api.client.manager.SaploTextManager;

public class TestingWithSaploAPI {
	public static void main(String args[]) throws Exception
	{
		SaploClient client = new SaploClient("a69ef0eb267e3578963a465b2b871208", "e88b050934321f88acb257fda2e892e2");
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
		        + " TagWord: " + tag.getTagWord());
	}
}