package org.nirbhaya.trending;
import java.io.IOException;
import com.google.gson.Gson;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.nirbhaya.trending.MongoQuery;

import com.google.gson.Gson;

@Path("/category-sewage")
public class CategoryTrendsSewageMongo {

	@GET
	@Produces( MediaType.APPLICATION_JSON )

	public String sayPlainTextHello(@QueryParam("callback") String callback)
	{
		catcontentSerialize catContent=null;
		Gson gson = new Gson();
		
		try {
			
			if(callback!=null){
				catContent=	gson.fromJson(MongoQuery.getCatTypes("type","sewage problems"), catcontentSerialize.class);
				return callback+"("+"{\"catContent\":"+gson.toJson(catContent.catContent)+"}"+")";
					
			}
			else{
				catContent=	gson.fromJson(MongoQuery.getCatTypes("type","sewage problems"), catcontentSerialize.class);
				return "{\"catContent\":"+gson.toJson(catContent.catContent)+"}";
					
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// This method is called if request is HTML


}
