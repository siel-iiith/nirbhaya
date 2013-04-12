package org.nirbhaya.trending;

import java.io.FileReader;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("/category-food1")
public class CategoryTrendsFood 
{
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String sendTypes(@QueryParam("callback") String callback) {
    	String line="";
    	Gson gson = new Gson();
    	catcontentSerialize catContent=null;
    	try
		{
    		catContent = gson.fromJson(new FileReader("Food wastage-Trends"), catcontentSerialize.class);
    		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return callback+"("+gson.toJson(catContent)+")";
    }

    @POST
    public String lowerCase(final String message) {
        return "Hi REST!".toLowerCase();
    }
}
