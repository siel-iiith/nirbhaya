package org.nirbhaya.rssfeeds;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author arpit
 */
public class RSSParse {
    
    public static void main(String args[]) throws MalformedURLException, IOException, InterruptedException{
        
        Map<String, Vector<NewsObject>> newsFeedTOI = RSSFeed.getTOINews("rssfeeds.txt");
        
        Vector<NewsObject> trendingTopicsFeedNDTV = RSSFeed.getNDTVNews("trending-stories.txt");
          
        Map<String, Vector<NewsObject>> newsFeedHindu = RSSFeed.getHinduNews("rssfeeds.txt");
        
    }

}