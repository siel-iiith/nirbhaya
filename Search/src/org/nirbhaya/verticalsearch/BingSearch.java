package org.nirbhaya.verticalsearch;
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
 * @author ajay, nikhil, jayant
 */

@Path("/BingSearch")
public class BingSearch {
	//static String AZURE_APPID="p9CfpJiKGm6btYTZbtyMdSNGwe4hk9rzXkurq6U1mTU=";
	static String AZURE_APPID=System.getProperty("BingKey");
	static QueryExpansion qE = new QueryExpansion();
	
	public static void main(String[] args)
	{
		System.out.println(AZURE_APPID);
	}
	 public ArrayList<BingSearchResult> getBingSearchResults(String query) {
	        System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
	        System.setProperty("http.proxyPort", "8080");
	        AzureSearchWebQuery aq = new AzureSearchWebQuery();
	        aq.setAppid(AZURE_APPID);
	        aq.setQuery(query);
	        aq.doQuery();
	        AzureSearchResultSet<AzureSearchWebResult> aswr = aq.getQueryResult();
	        ArrayList<BingSearchResult> bsrList=new ArrayList<BingSearchResult>();
	        
	        for (AzureSearchWebResult a : aswr){
	            BingSearchResult bsr=new BingSearchResult(a.getUrl(),a.getTitle(),a.getDescription());            
	            bsrList.add(bsr);
	        }
	        return bsrList;
	}
	 
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String searchBing (@QueryParam("q") String query, @QueryParam("callback") String callback) throws Exception {
    	
    	
    	query = qE.expandedQuery(query);
    	
        ArrayList<BingSearchResult> results=getBingSearchResults(query);
        Iterator<BingSearchResult> it=results.iterator();
        BingSearchResult current;
        
        String result = "{\"searchResults\": [";
        
        while(it.hasNext()) {
        	current = it.next();
        	String t1=current.getURL();
        	String t2=current.getTitle();
        	String t3=current.getSnippet();
        	
        	t1=t1.replaceAll("<[^>]*", "");
        	t1=t1.replaceAll("\"", "&quot;");
        	t1=t1.replaceAll("\'", " ");
        	
        	t2=t2.replaceAll("<[^>]*", "");
        	t2=t2.replaceAll("\"", "&quot;");
        	t2=t2.replaceAll("\'", " ");
        	
        	t3=t3.replaceAll("<[^>]*", "");
        	t3=t3.replaceAll("\"", "&quot;");
        	t3=t3.replaceAll("\'", " ");        	
        	
        	result += "{ " + "\"jBingURL\":\"" + t1 +
                    "\" , \"jBingTitle\":\"" + t2 +
                    "\" , \"jBingDesc\":\"" + t3 +
                  "\" }," + "\n";
        	        	
        }
        result=result.substring(0, result.length()-2);
        result += "]" + "\n" + "}";
        result = callback+"("+result+");";
        
        return result;
    }
}    
   
    
