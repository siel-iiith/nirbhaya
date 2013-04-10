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
public class HelloService {

	@GET
	@Produces( MediaType.APPLICATION_JSON )

	public String sayPlainTextHello(@QueryParam("location") String location, @QueryParam("problem") String problem, @QueryParam("callback") String callback)
	{
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("HeatMap QueryLog: "+dateFormat.format(cal.getTime())+":"+location+"_"+problem+"_"+callback);
			if(callback!=null){
				if(location==null&&problem==null)
					return callback+"("+UrlQuery.getAllProblem()+")";
				else if(location!=null && problem==null)
					return callback+"("+UrlQuery.getLocationProblem(location)+")";
				else if(location==null && problem!=null)
					return callback+"("+UrlQuery.getProblemLocation(problem)+")";
				else if(location!=null && problem!=null)
					return callback+"("+UrlQuery.getBothLocationProblem(location, problem)+")";
			}
			else{
				if(location==null&&problem==null)
					return UrlQuery.getAllProblem();
				else if(location!=null && problem==null)
					return UrlQuery.getLocationProblem(location);
				else if(location==null && problem!=null)
					return UrlQuery.getProblemLocation(problem);
				else if(location!=null && problem!=null)
					return UrlQuery.getBothLocationProblem(location, problem);
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
