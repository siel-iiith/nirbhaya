package org.nirbhaya.trending;
import java.io.IOException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.nirbhaya.trending.MongoQuery;

@Path("/trending-types1")
public class CatTypesMongo {

	@GET
	@Produces( MediaType.APPLICATION_JSON )

	public String sayPlainTextHello(@QueryParam("callback") String callback)
	{
		System.out.println("hello");
		try {
			
			if(callback!=null){
					return callback+"("+CatTypesMongoQuery.getCatTypes("categories.name","Civic & Administration")+")";
			}
			else{
				
					return MongoQuery.getCatTypes("categories.name","Civic & Administration");
			}
			//return callback+"("+MongoQuery.getLocationProblem(query)+")";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// This method is called if request is HTML


}
