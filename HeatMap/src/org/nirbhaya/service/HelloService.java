package org.nirbhaya.service;
import java.io.IOException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.nirbhaya.heatmap.MongoQuery;

@Path("/test")
public class HelloService {
	
	@GET
	@Produces( MediaType.APPLICATION_JSON )

	public String sayPlainTextHello(@QueryParam("location") String location, @QueryParam("problem") String problem, @QueryParam("callback") String callback)
	{
		try {
			if(location==null&&problem==null)
			return callback+"("+MongoQuery.getAllProblem()+")";
			else if(location!=null && problem==null)
				return callback+"("+MongoQuery.getLocationProblem(location)+")";
			else if(location==null && problem!=null)
				return callback+"("+MongoQuery.getProblemLocation(problem)+")";
			else if(location!=null && problem!=null)
				return callback+"("+MongoQuery.getBothLocationProblem(location, problem)+")";
			//return callback+"("+MongoQuery.getLocationProblem(query)+")";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// This method is called if request is HTML
	
	
}
