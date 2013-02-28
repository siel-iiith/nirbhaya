package shopping.cart.service;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Iterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import net.billylieurance.azuresearch.AzureSearchResultSet;
import net.billylieurance.azuresearch.AzureSearchWebQuery;
import net.billylieurance.azuresearch.AzureSearchWebResult;

/**
 *
 * @author ajay
 */

@Path("/BingSearch")
public class BingSearch {
	static String AZURE_APPID="p9CfpJiKGm6btYTZbtyMdSNGwe4hk9rzXkurq6U1mTU=";
	
	 public ArrayList<BingSearchResult> getBingSearchResults(String query) {
	        System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
	        System.setProperty("http.proxyPort", "8080");
	        AzureSearchWebQuery aq = new AzureSearchWebQuery();
	        aq.setAppid(AZURE_APPID);
	        aq.setQuery(query);
	        aq.doQuery();
	        AzureSearchResultSet<AzureSearchWebResult> aswr = aq.getQueryResult();
	        ArrayList<BingSearchResult> bsrList=new ArrayList<>();
	        
	        for (AzureSearchWebResult a : aswr){
	            BingSearchResult bsr=new BingSearchResult(a.getUrl(),a.getTitle(),a.getDescription());            
	            bsrList.add(bsr);
	        }
	        return bsrList;
	}
	 
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String searchBing (@QueryParam("q") String query) {
        ArrayList<BingSearchResult> results=getBingSearchResults(query);
        Iterator<BingSearchResult> it=results.iterator();
        BingSearchResult current;
        
        String result = "";
        
        while(it.hasNext()) {
            current=it.next();
//            System.out.println("\nURL: "+current.getURL());
//            System.out.println("\nTitle: "+current.getTitle());
//            System.out.println("\nSnippet: "+current.getSnippet());
            
            result += "{ " + "\"jBingURL\":\"" + current.getURL() +
                    "\" , \"jBingTitle\":\"" + current.getTitle() +
                    "\" , \"jBingDesc\":\"" + current.getSnippet() +
                    "\" }," + "\n"; 
        }
        result += "]" + "\n" + "}";
//        System.out.println(result);
        return result;
    }
    
   
    
//    public static void main(String args[]) {
//        BingSearch bs=new BingSearch();
//        String query="india australia";
//        bs.searchBing(query);                
//    }        
}


/*
"employees": [
{ "firstName":"John" , "lastName":"Doe" }, 
{ "firstName":"Anna" , "lastName":"Smith" }, 
{ "firstName":"Peter" , "lastName":"Jones" }
]
}
*/