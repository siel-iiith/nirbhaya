package org.nirbhaya.rssfeeds;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author arpit
 */
public class RSSFeed {
    
    public static Map<String, Vector<NewsObject>> getTOINews(String rssPage) throws MalformedURLException, InterruptedException, IOException{
        
        String DIR = "toi";
        FetchPage.saveUrlPage("http://timesofindia.indiatimes.com/rss.cms", rssPage);
        
        Map<String, String> urlList = FetchPage.getTOIUrlLocations(rssPage);

        for (Map.Entry<String, String> entry : urlList.entrySet())
            FetchPage.saveUrlPage(entry.getValue(), DIR+"/"+entry.getKey()); // toi for Times of India
        
        
        // NewsFeed TOI contains all the data pertaining to all the cities stored according
        // to the cities. NewsObject has certain attributes for better classification.
        Map<String, Vector<NewsObject>> newsFeedTOI = new HashMap<String, Vector<NewsObject>>();
                
        File dir = new File(DIR);
             
        String[] children = dir.list();
        for (String fileName : children) {
            Vector<NewsObject> news = FillNewsObject.getTOINewsFeed(DIR+"/"+fileName);
            newsFeedTOI.put(fileName, news);
        }
        
        return newsFeedTOI;
           
        /*    for(Map.Entry<String, Vector<NewsObject>> entry : newsFeedTOI.entrySet()){
            System.out.println("***********file "+entry.getKey()+"*****************");
            for(NewsObject newsObject : entry.getValue()){
                System.out.println("****"+newsObject.title + " ------\n "+newsObject.description);
            }
            System.out.println("******file ends*******");
        }*/
        
    }
    
    // trending stories from NDTV
    public static Vector<NewsObject> getNDTVNews(String rssPage) throws MalformedURLException, InterruptedException, IOException{
        
        String DIR = "ndtv";
        FetchPage.saveUrlPage("http://feeds.feedburner.com/NDTV-Trending?format=xml", DIR+"/"+rssPage);
     
        // NDTV has trending stories RSS Feeds. However these feeds are not marked by 
        // location, type and other location.
        Vector<NewsObject> newsFeedNDTV = FillNewsObject.getNDTVNewsFeed(DIR+"/"+rssPage);
        
        for(NewsObject newsObject : newsFeedNDTV){
                System.out.println("****"+newsObject.title + " ------\n "+newsObject.description);
        }
        
        return newsFeedNDTV;
    }
    
    // RSS feeds from The Hindu
    public static Map<String, Vector<NewsObject>> getHinduNews(String rssPage) throws MalformedURLException, InterruptedException, IOException{
        
        String DIR = "hindu";
        FetchPage.saveUrlPage("http://hindu.com/rss", rssPage);
        
        Map<String, String> urlList = FetchPage.getHinduUrlLocations(rssPage);

        for (Map.Entry<String, String> entry : urlList.entrySet())
            FetchPage.saveUrlPage(entry.getValue(), DIR+"/"+entry.getKey()); // toi for Times of India
        
        
        // NewsFeed TOI contains all the data pertaining to all the cities stored according
        // to the cities. NewsObject has certain attributes for better classification.
        Map<String, Vector<NewsObject>> newsFeedHindu = new HashMap<String, Vector<NewsObject>>();
                
        File dir = new File(DIR);
             
        String[] children = dir.list();
        for (String fileName : children) {
            Vector<NewsObject> news = FillNewsObject.getHinduNewsFeed(DIR+"/"+fileName);
            newsFeedHindu.put(fileName, news);
        }
        
        // description is empty because RSS Feeds by Hindu is empty.
        return newsFeedHindu;
                   
     /*   for(Map.Entry<String, Vector<NewsObject>> entry : newsFeedHindu.entrySet()){
            System.out.println("***********file "+entry.getKey()+"*****************");
            for(NewsObject newsObject : entry.getValue()){
                System.out.println("****"+newsObject.title + " ------\n "+newsObject.description);
            }
            System.out.println("******file ends*******");
        }
       */ 
    }    
    
}
