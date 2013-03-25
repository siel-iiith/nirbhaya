package org.nirbhaya.trending;
/**
 * 
 */

/**
 * @author sandeep
 *
 */



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;


import com.google.gson.Gson;
import net.billylieurance.azuresearch.AzureSearchNewsQuery;
import net.billylieurance.azuresearch.AzureSearchNewsResult;
import net.billylieurance.azuresearch.AzureSearchResultSet;
import net.billylieurance.azuresearch.test.AbstractAzureSearchTest;


public class BingSearch extends AbstractAzureSearchTest{
	AzureSearchNewsResult asr;
	File file;
	FileWriter fstream;
	BufferedWriter out1;
	static String AZURE_APPID="CxlTqDgVZvioFczwIr5bz/+nqEdP98TU9PmDcEUk7z0=";
	public BingSearch()
	{

	}
	public String searchBing (String query) 
	{
		ArrayList<BingSearchResult> results=getBingSearchResults(query);
		Iterator<BingSearchResult> it=results.iterator();
		BingSearchResult current;
		String result=null;
		while(it.hasNext()) {
			current=it.next();
			Gson gson = new Gson();
			SearchResult r = new SearchResult(current.getTitle(),current.getURL(),current.getSnippet(),current.getDate(),current.getSource());
			result = gson.toJson(r);
			System.out.println(result);
			try
			{
				file = new File("/home/sandeep/roadrepairs/road25.txt");
				fstream = new FileWriter(file.getAbsoluteFile());
				out1 = new BufferedWriter(fstream);
				out1.write(result);
				out1.close();
			}catch(Exception e)
			{

			}
		}
		return result;
	}
	public ArrayList<BingSearchResult> getBingSearchResults(String query) {
		System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
		System.setProperty("http.proxyPort", "8080");
		AzureSearchNewsQuery aq = new AzureSearchNewsQuery();
		aq.setAppid(AZURE_APPID);
		aq.setQuery(query);
		//aq.setSkip(90);
		aq.doQuery();
	
		AzureSearchResultSet<AzureSearchNewsResult> aswr = aq.getQueryResult();
		ArrayList<BingSearchResult> bsrList=new ArrayList<BingSearchResult>();
		for (AzureSearchNewsResult a : aswr)
		{
			
			BingSearchResult bsr=new BingSearchResult(a.getUrl(),a.getTitle(),a.getDescription(),a.getDate(),a.getSource());
			bsrList.add(bsr);
		}
		return bsrList;
	}
	public static void main(String args[]) {
		BingSearch bs=new BingSearch();
		String query="hazardous roads";
		bs.searchBing(query);
		bs.searchBing(query);
	}
}



