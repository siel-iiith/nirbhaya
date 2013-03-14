package org.nirbhaya.trending;
import java.io.File;
import java.io.FileReader;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("/trending-types")
public class Types {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String sendTypes(@QueryParam("callback") String callback) {
    	String line="";
    	Gson gson = new Gson();
    	CategoryTypes j=null;
    	try
		{
    		
    		j = gson.fromJson(new FileReader("types.txt"), CategoryTypes.class);
		    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return callback+"("+gson.toJson(j)+")";
    }

    @POST
    public String lowerCase(final String message) {
        return "Hi REST!".toLowerCase();
    }
}

