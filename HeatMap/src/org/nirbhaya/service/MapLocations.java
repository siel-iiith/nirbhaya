package org.nirbhaya.service;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.nirbhaya.heatmap.UrlQuery;

@Path("/test")
public class MapLocations {

	@GET
	@Produces( MediaType.APPLICATION_JSON )

	public String sayPlainTextHello(@QueryParam("location") String location, @QueryParam("problem") String problem, @QueryParam("callback") String callback)
	{
		try {
			UrlQuery query= new UrlQuery();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("HeatMap QueryLog: "+dateFormat.format(cal.getTime())+":"+location+"_"+problem+"_"+callback);
			if(callback!=null){
				if(location==null&&problem==null)
					return callback+"("+query.getAllProblem()+")";
				else if(location!=null && problem==null)
					return callback+"("+query.getLocationProblem(location)+")";
				else if(location==null && problem!=null)
					return callback+"("+query.getProblemLocation(problem)+")";
				else if(location!=null && problem!=null)
					return callback+"("+query.getBothLocationProblem(location, problem)+")";
			}
			else{
				if(location==null&&problem==null)
					return query.getAllProblem();
				else if(location!=null && problem==null)
					return query.getLocationProblem(location);
				else if(location==null && problem!=null)
					return query.getProblemLocation(problem);
				else if(location!=null && problem!=null)
					return query.getBothLocationProblem(location, problem);
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
