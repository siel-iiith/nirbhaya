package org.nirbhaya.rssfeeds;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author arpit
 */
public class FillNewsObject {
    
    public static Pattern itemP = Pattern.compile("<item>(.*?)</item>");
    
    public static Pattern titleP = Pattern.compile("<title>(.*?)</title>");
    public static Pattern descriptionP = Pattern.compile("<description>(.*?)</description>");
    public static Pattern guidP = Pattern.compile("<guid>(.*?)</guid>");
    public static Pattern linkP = Pattern.compile("<link>(.*?)</link>");
    public static Pattern pubDateP = Pattern.compile("<pubDate>(.*?)</pubDate>");
    
    public static NewsObject Fill(String newsItem){
       NewsObject newsObject = new NewsObject();
       
       Matcher m = titleP.matcher(newsItem);
       if(m.find()){
           newsObject.title = m.group(1);
       }
       
       m = descriptionP.matcher(newsItem);
       if(m.find()){
           newsObject.description = m.group(1);
       }
       
       m = guidP.matcher(newsItem);
       if(m.find()){
           newsObject.guid = m.group(1);
       }
       
       m = linkP.matcher(newsItem);
       if(m.find()){
           newsObject.link = m.group(1);
       }
       
       m = pubDateP.matcher(newsItem);
       if(m.find()){
           newsObject.pubDate = m.group(1);
       }
       
       return newsObject;
    }
    
    public static Vector<NewsObject> getTOINewsFeed(String fileName) throws FileNotFoundException, IOException{
        Vector<NewsObject> newsObjectList = new Vector<NewsObject>();   // contains all the news Objects
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String completeFile = "", line;
        
        while((line = bufferedReader.readLine()) != null){
            completeFile += line;
        }
        
        Matcher m = itemP.matcher(completeFile);
        while(m.find()){
            newsObjectList.add(Fill(m.group(1)));
        }
        return newsObjectList;
    }
    
    public static Vector<NewsObject> getNDTVNewsFeed(String fileName) throws FileNotFoundException, IOException{
        Vector<NewsObject> newsObjectList = new Vector<NewsObject>();   // contains all the news Objects
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String completeFile = "", line;
        
        while((line = bufferedReader.readLine()) != null){
            completeFile += line;
        }
        
        Matcher m = itemP.matcher(completeFile);
        while(m.find()){
            newsObjectList.add(Fill(m.group(1)));
        }
        return newsObjectList;
    }
    
    public static Vector<NewsObject> getHinduNewsFeed(String fileName) throws FileNotFoundException, IOException{
        Vector<NewsObject> newsObjectList = new Vector<NewsObject>();   // contains all the news Objects
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String completeFile = "", line;
        
        while((line = bufferedReader.readLine()) != null){
            completeFile += line;
        }
        
        Matcher m = itemP.matcher(completeFile);
        while(m.find()){
            newsObjectList.add(Fill(m.group(1)));
        }
        return newsObjectList;
    }
    
}