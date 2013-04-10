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

import org.nirbhaya.heatmap.MessageQuery;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.util.JSON;

@Path("/mapmessage")
public class MapMessage {
	@GET
	@Produces( MediaType.APPLICATION_JSON )

	public String sayPlainTextHello(@QueryParam("location") String location, @QueryParam("problem") String problem, @QueryParam("callback") String callback)
	{
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println("HeatMap/MapMessage QueryLog: "+dateFormat.format(cal.getTime())+":"+location+"_"+problem+"_"+callback);
			JsonParser parser = new JsonParser();
			
			JsonObject json=new JsonObject();
			String value="";
			if(location.equals("null")&&problem.equals("null")){
				
				value= "All Results";
			}
			else if(!location.equals("null") && problem.equals("null")){
				if(parser.parse(MessageQuery.getLocationProblem(location)).getAsJsonObject().get("result").getAsJsonArray().size()>0)
					value= "Results for "+location+". Zoom out for all Results.";
				else
					value= "No results for "+location+". Showing All Results.";
			}
			else if(location.equals("null") && !problem.equals("null")){
				System.out.println(MessageQuery.getProblemLocation(problem));
				if(parser.parse(MessageQuery.getProblemLocation(problem)).getAsJsonObject().get("result").getAsJsonArray().size()>0)
					value= "Results for "+problem;
				else
					value= "No results for "+problem+". Showing All Results.";
				
			}
			else if(!location.equals("null") && !problem.equals("null"))
				if(parser.parse(MessageQuery.getBothLocationProblem(location, problem)).getAsJsonObject().get("result").getAsJsonArray().size()>0)
					value= "Results for "+problem+" in "+location;
				else
					value= "No results for "+problem+" in "+location+". Showing All Results.";
			
			json.addProperty("results", value);
			return callback+"("+json.toString()+")";
			//return callback+"("+MongoQuery.getLocationProblem(query)+")";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Showing All Results";
	}
}
