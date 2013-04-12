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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;


import com.google.gson.Gson;
import net.billylieurance.azuresearch.AzureSearchNewsQuery;
import net.billylieurance.azuresearch.AzureSearchNewsResult;
import net.billylieurance.azuresearch.AzureSearchResultSet;
import net.billylieurance.azuresearch.test.AbstractAzureSearchTest;


public class BingSearch extends AbstractAzureSearchTest
{
	AzureSearchNewsResult asr;
	PrintWriter pr = null;
	FileWriter fstream;
	BufferedWriter out1;
	static String AZURE_APPID="CxlTqDgVZvioFczwIr5bz/+nqEdP98TU9PmDcEUk7z0=";
	public BingSearch()
	{

	}
	public String searchBing (String query, int skip,String path) 
	{
		ArrayList<BingSearchResult> results=getBingSearchResults(query, skip);
		Iterator<BingSearchResult> it=results.iterator();
		BingSearchResult current;
		String result=null;
		while(it.hasNext()) {
			current=it.next();
			Gson gson = new Gson();
			BingSearchResult r = new BingSearchResult(current.getURL(),current.getTitle(),current.getSnippet(),current.getDate(),current.getSource());
			result = gson.toJson(r);
			try
			{
				pr = new PrintWriter(new BufferedWriter(new FileWriter(path+query, true)));
				pr.println(result);
				pr.flush();
				pr.close();
			}catch(Exception e)
			{

			}
		}
		return result;
	}
	public ArrayList<BingSearchResult> getBingSearchResults(String query, int skip) {
		System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
		System.setProperty("http.proxyPort", "8080");
		AzureSearchNewsQuery aq = new AzureSearchNewsQuery();
		aq.setAppid(AZURE_APPID);
		aq.setQuery(query);
		//aq.setSkip(90);
		aq.doQuery();
		AzureSearchResultSet<AzureSearchNewsResult> aswr = aq.getQueryResult();
		ArrayList<BingSearchResult> bsrList=new ArrayList<BingSearchResult>();
		for (AzureSearchNewsResult a : aswr){
			BingSearchResult bsr=new BingSearchResult(a.getUrl(),a.getTitle(),a.getDescription(),a.getDate(),a.getSource());
			bsrList.add(bsr);
		}
		return bsrList;
	}
	public static void main(String args[]) {
		BingSearch bs=new BingSearch();
		String query[]={"hazardous roads", "water logging", "electricity problems", "Food wastage", "sewage problems"};
		for(int i = 0 ; i < query.length; i++)
		{
			for(int j = 0 ; j < 3; j++)
			{
				bs.searchBing(query[i], j*15,args[0]);
			}
		}
	}
}