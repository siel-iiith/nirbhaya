package shopping.cart.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/create")
public class HelloResource {
//	@GET
//	@Produces({MediaType.TEXT_PLAIN})
////	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//	public String sayHello() {
//		return "Hello Jersey";
//	}
	
	public String f (String str) {
		return str + " ho ha " + str;
	}
	
	@GET	
	@Produces({MediaType.TEXT_PLAIN})
	public String createFromGet(
	        @QueryParam("q") String query) {	    
	    return f(query);
	}
}       